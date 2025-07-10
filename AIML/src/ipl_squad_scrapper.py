import requests
from bs4 import BeautifulSoup
import pandas as pd
import os
import urllib.parse

def get_wikipedia_url(player_name):
    return f"https://en.wikipedia.org/wiki/{urllib.parse.quote(player_name.replace(' ', '_'))}"

def extract_infobox(soup):
    infobox = soup.find("table", class_="infobox")
    if not infobox:
        return None

    personal_info = {}
    international_info = {}
    domestic_teams = []

    current_section = "personal"

    for row in infobox.find_all("tr"):
        header = row.find("th")
        data = row.find("td")

        if not header or not data:
            continue

        label = header.get_text(strip=True)
        value = data.get_text(separator=" ", strip=True)

        # Use keywords to categorize sections
        if "International" in label:
            current_section = "international"
        elif "Domestic" in label or "Club" in label or "Team" in label:
            current_section = "domestic"

        if current_section == "personal":
            personal_info[label] = value
        elif current_section == "international":
            international_info[label] = value
        elif current_section == "domestic":
            domestic_teams.append({label: value})

    return personal_info, international_info, domestic_teams

def extract_career_stats(soup, player_name):
    tables = soup.find_all("table", class_="wikitable")
    for table in tables:
        if "Statistics" in table.text or "Career" in table.text:
            try:
                df = pd.read_html(str(table))[0]
                df.insert(0, "Player", player_name)
                return df
            except:
                continue
    return None

def scrape_player(player_name):
    url = get_wikipedia_url(player_name)
    try:
        response = requests.get(url, timeout=10)
        response.raise_for_status()
        soup = BeautifulSoup(response.text, 'html.parser')

        personal_info, international_info, domestic_info = extract_infobox(soup)
        stats_df = extract_career_stats(soup, player_name)

        # Create output folder
        os.makedirs("datasets/wikipedia_players", exist_ok=True)

        # Save each section as a CSV
        pd.DataFrame([personal_info]).to_csv(f"datasets/wikipedia_players/{player_name.replace(' ', '_')}_personal.csv", index=False)
        pd.DataFrame([international_info]).to_csv(f"datasets/wikipedia_players/{player_name.replace(' ', '_')}_international.csv", index=False)
        pd.DataFrame(domestic_info).to_csv(f"datasets/wikipedia_players/{player_name.replace(' ', '_')}_domestic.csv", index=False)
        if stats_df is not None:
            stats_df.to_csv(f"datasets/wikipedia_players/{player_name.replace(' ', '_')}_stats.csv", index=False)

        print(f"✅ Scraped {player_name}")
    except Exception as e:
        print(f"❌ Failed for {player_name}: {e}")

def process_players(player_list):
    for player in player_list:
        scrape_player(player)

# 🔽 List of players you want to scrape
players = [
    "Virat Kohli",
    "MS Dhoni",
    "Rohit Sharma",
    "Ravindra Jadeja"
]

process_players(players)
