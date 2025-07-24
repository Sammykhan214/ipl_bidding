# 🏏 IPL Auction Backend Progress Report

## 📅 Duration: Last 2–3 Days
## 👨‍💻 Developer: Sagar Kasana

---

## ✅ Major Features Implemented

### 1. 📁 CSV-Based Data Import (Player + Stats)
- Created `ipl_player.csv` and `all_player_data.csv` datasets
- Cleaned corrupted/misaligned CSV fields (e.g., extra fields in BattingStyle like "Batsman,Right-Handed")
- Handled cases where player has zero matches (skip importing invalid stats)
- Designed import to use:
  - `CsvUtils.java` for reading CSV into DTOs
  - DTOs (`PlayerCsvDTO`, `PlayerStatsDTO`) using plain Strings
- Ensured type conversion (`String` → `Double`, `Integer`) with error handling

---

### 2. 🛠 Entity & DB Mapping
- ✅ `Player` entity (basic info)
- ✅ `PlayerStats` entity (batting + bowling stats)
- Mapped with:
  - `@OneToOne` relation from `PlayerStats → Player` using `@JoinColumn`
  - Bidirectional setup with `mappedBy` in `Player`
- Ensured correct schema where:
  - Foreign key exists **only** in `player_stats.player_id`
  - No duplicate columns in `player` table

---

### 3. 🧪 Data Import Service
- Created full import service using:
  ```java
  Optional<Player> player = playerRepo.findByName(name);
  if (player.isPresent()) {
     stats.setPlayer(player.get());
     playerStatsRepo.save(stats);
  } else {
     log.warn("Skipping player: {} not found in DB", name);
  }
