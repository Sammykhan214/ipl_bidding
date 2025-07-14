import os
import requests
import pandas as pd
from bs4 import BeautifulSoup
from io import StringIO

def get_wikipedia_data(name, url):
    print(f"\n🔍 Fetching data for: {name}")
    response = requests.get(url)
    if response.status_code != 200:
        print(f"❌ Failed to fetch {url}")
        return

    soup = BeautifulSoup(response.content, "html.parser")
    output_dir = f"datasets/players/{name}"
    os.makedirs(output_dir, exist_ok=True)

    # -------- 1. PERSONAL INFO (Infobox) --------
    infobox = soup.find("table", class_="infobox")
    personal_info = {}
    if infobox:
        for row in infobox.find_all("tr"):
            if row.th and row.td:
                key = row.th.get_text(strip=True)
                val = row.td.get_text(" ", strip=True).replace('\xa0', ' ')
                personal_info[key] = val

    if personal_info:
        pd.DataFrame([personal_info]).to_csv(f"{output_dir}/personal_info.csv", index=False)
        print("✅ personal_info.csv saved")
    else:
        print("⚠️ No personal info found.")

    # -------- 2. STATS TABLES (Wikitables) --------
    wikitable_list = soup.find_all("table", class_="wikitable")
    if not wikitable_list:
        print("⚠️ No stat tables found.")

    for idx, table in enumerate(wikitable_list):
        try:
            df = pd.read_html(StringIO(str(table)))[0]
            df.to_csv(f"{output_dir}/table_{idx}.csv", index=False)
            print(f"✅ table_{idx}.csv saved")
        except Exception as e:
            print(f"⚠️ Could not parse table {idx}: {e}")

if __name__ == "__main__":
    players = {
        "MS_Dhoni": "https://en.wikipedia.org/wiki/MS_Dhoni",
        "Ravindra_Jadeja": "https://en.wikipedia.org/wiki/Ravindra_Jadeja"
    }

    for name, url in players.items():
        get_wikipedia_data(name, url)
