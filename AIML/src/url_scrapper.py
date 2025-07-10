import json

# Step 1: Read names from text file
with open("datasets/player_names.txt", "r", encoding="utf-8") as f:
    names = [line.strip() for line in f if line.strip()]

# Step 2: Create dictionary with empty string as URL
player_dict = {name: "" for name in names}

# Step 3: Write to JSON file
with open("datasets/player_links.json", "w", encoding="utf-8") as json_file:
    json.dump(player_dict, json_file, indent=4)

print("✅ player_links.json created successfully.")
