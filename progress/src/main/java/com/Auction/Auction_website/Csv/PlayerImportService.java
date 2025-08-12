package com.Auction.Auction_website.Csv;

import com.Auction.Auction_website.DTO.iplInfoDTO;
import com.Auction.Auction_website.DTO.playerStatsDTO;
import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Entity.PlayerStats;
import com.Auction.Auction_website.Repository.Player_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerImportService {
    @Autowired
    Player_Repo player_repo;

    private static final Logger log = LoggerFactory.getLogger(PlayerImportService.class);
    public void ImportPlayercsv() throws IOException {
        List<iplInfoDTO> players = CsvUtil.read(iplInfoDTO.class, "src/main/resources/data/ipl_player.csv");

        for (iplInfoDTO data : players) {
            Player player = new Player();
            player.setName(data.getName().trim());
            player.setNationality(data.getNationality());
            player.setBattingStyle(data.getBattingStyle());
            player.setBowlingStyle(data.getBowlingStyle());
            player.setBasePrice(100000.0);
            player_repo.save(player);
        }
    }
    private Integer parseIntIfNonZero(String value) {
        try {
            if (value == null || value.trim().isEmpty() || value.equalsIgnoreCase("N/A")) return 0;
            Double d = Double.parseDouble(value.trim());
            int intValue = d.intValue();
            return intValue;
        } catch (NumberFormatException e) {
            log.warn("Invalid int: {}", value);
            return null;
        }
    }

    private Double parseDoubleIfNonZero(String value) {
        try {
            if (value == null || value.trim().isEmpty() || value.equalsIgnoreCase("N/A")) return 0.0;
            double doubleValue = Double.parseDouble(value.trim());
            return doubleValue;
        } catch (NumberFormatException e) {
            log.warn("Invalid double: {}", value);
            return null;
        }
    }
    public void ImportPlayerStatsCsv() throws IOException {
        List<playerStatsDTO> list = CsvUtil.read(playerStatsDTO.class, "src/main/resources/data/all_time_player_stats.csv");

        for (playerStatsDTO data : list) {
            Optional<Player>player_optional = player_repo.findByName(data.getName());
            if(player_optional.isPresent()){
                Player player = player_optional.get();
                PlayerStats stats = new PlayerStats();
                stats.setPlayer(player);
                stats.setName(player.getName());

                // Batting
                Double avg = parseDoubleIfNonZero(data.getBatting_average());
                if (avg != null) stats.setBatting_average(avg);

                Double strikeRate = parseDoubleIfNonZero(data.getStrikeRate());
                if (strikeRate != null) stats.setStrikeRate(strikeRate);

                Integer runs = parseIntIfNonZero(data.getRuns());
                if (runs != null) stats.setRuns(runs);

                Integer fifties = parseIntIfNonZero(data.getFifties());
                if (fifties != null) stats.setFifties(fifties);

                Integer centuries = parseIntIfNonZero(data.getCenturies());
                if (centuries != null) stats.setCenturies(centuries);

                Integer hs = parseIntIfNonZero(data.getHighest_score());
                if (hs != null) stats.setHighest_score(hs);

                // Bowling
                Double economy = parseDoubleIfNonZero(data.getEconomy());
                if (economy != null) stats.setEconomy(economy);

                Integer wickets = parseIntIfNonZero(data.getWickets());
                if (wickets != null) stats.setWickets(wickets);

                Integer fiveWkts = parseIntIfNonZero(data.getFiveWicketHauls());
                if (fiveWkts != null) stats.setFiveWicketHauls(fiveWkts);

                Double bowlAvg = parseDoubleIfNonZero(data.getBowling_average());
                if (bowlAvg != null) stats.setBowling_average(bowlAvg);

                // Matches (take max of batting/bowling)
                Integer batMatches = parseIntIfNonZero(data.getBatting_matches());
                Integer bowlMatches = parseIntIfNonZero(data.getBowling_matches());

                if (batMatches != null || bowlMatches != null) {
                    int maxMatches = Math.max(batMatches != null ? batMatches : 0, bowlMatches != null ? bowlMatches : 0);
                    stats.setMatches(maxMatches);}
                player.setPlayerStats(stats);
                player_repo.save(player);
                log.info("Saved stats for player: {}", data.getName());
            }
        else{
            log.warn("Skipping player: {} - not found in DB", data.getName());
        }
        }

}}
