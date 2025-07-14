import os
import json

# Path to the player name file
file_path = "datasets/player_names.txt"
json_path = "datasets/player_links.json"

# Read player names
with open(file_path, "r", encoding="utf-8") as f:
    player_names = f.readlines()

# Build dictionary with cleaned names
players_dict = {}
for name in player_names:
    clean_name = name.strip().replace(" ", "_")
    players_dict[clean_name] = ""  # Leave URL blank for manual entry

# Write to JSON file
with open(json_path, "w", encoding="utf-8") as json_file:
    json.dump(players_dict, json_file, indent=4)

print(f"players_links.json created successfully with {len(players_dict)} entries.")
