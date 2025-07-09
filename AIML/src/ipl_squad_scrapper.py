import requests
from bs4 import BeautifulSoup
import csv

url = "https://www.cricbuzz.com/profiles/1413/virat-kohli"
res = requests.get(url)
res.raise_for_status()
soup = BeautifulSoup(res.text, 'html.parser')

def extract_career(section_title):
    div = soup.find("div", text=section_title)
    if not div:
        raise ValueError(f"{section_title} section not found")
    table = div.find_next("table")
    headers = [th.get_text(strip=True) for th in table.find_all('th')[1:]]  # excluding first col
    data = {}
    for row in table.find_all('tr')[1:]:  # skip header row
        cols = row.find_all('td')
        format_name = cols[0].get_text(strip=True)
        stats = [td.get_text(strip=True) for td in cols[1:]]
        data[format_name] = dict(zip(headers, stats))
    return headers, data

# Extract batting and bowling sections
bat_headers, batting_data = extract_career("Batting Career Summary")
bowl_headers, bowling_data = extract_career("Bowling Career Summary")

# Display in console
print("=== Batting Career Summary ===")
for fmt, stats in batting_data.items():
    print(f"\nFormat: {fmt}")
    for h in bat_headers:
        print(f"  {h}: {stats[h]}")

print("\n=== Bowling Career Summary ===")
for fmt, stats in bowling_data.items():
    print(f"\nFormat: {fmt}")
    for h in bowl_headers:
        print(f"  {h}: {stats[h]}")

# Save to CSV
csv_file = "datasets/players/kohli_career_summary.csv"
with open(csv_file, 'w', newline='') as f:
    w = csv.writer(f)
    # header row
    top_header = ["Format"] \
         + [f"Batting_{h}" for h in bat_headers] \
         + [f"Bowling_{h}" for h in bowl_headers]
    w.writerow(top_header)

    all_formats = sorted(set(batting_data) | set(bowling_data))
    for fmt in all_formats:
        row = [fmt]
        b = batting_data.get(fmt, {})
        o = bowling_data.get(fmt, {})
        for h in bat_headers:
            row.append(b.get(h, ""))
        for h in bowl_headers:
            row.append(o.get(h, ""))
        w.writerow(row)

print(f"\n✅ CSV saved: {csv_file}")
