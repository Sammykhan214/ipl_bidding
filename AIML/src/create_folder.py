import os

# Path to the player name file
file_path = "datasets/player_names.txt"

# Read player names
with open(file_path, "r", encoding="utf-8") as f:
    player_names = f.readlines()

# Clean and create directories
for name in player_names:
    clean_name = name.strip().replace(" ", "_")
    folder_path = os.path.join("datasets/players", clean_name)
    os.makedirs(folder_path, exist_ok=True)

print("All player folders created successfully.")
