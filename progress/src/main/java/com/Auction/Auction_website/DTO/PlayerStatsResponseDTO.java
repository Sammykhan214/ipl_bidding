package com.Auction.Auction_website.DTO;

import lombok.Data;

@Data
public class PlayerStatsResponseDTO {
    private Integer runs;
    private Double batting_average;
    private Double strikeRate;
    private Integer fifties;
    private Integer centuries;
    private Integer matches;
    private Integer highest_score;

    private Double economy;
    private Integer wickets;
    private Integer fiveWicketHauls;
    private Double bowling_average;

    public Integer getRuns() {
        return runs;
    }

    public void setRuns(Integer runs) {
        this.runs = runs;
    }

    public Double getBatting_average() {
        return batting_average;
    }

    public void setBatting_average(Double batting_average) {
        this.batting_average = batting_average;
    }

    public Double getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(Double strikeRate) {
        this.strikeRate = strikeRate;
    }

    public Integer getFifties() {
        return fifties;
    }

    public void setFifties(Integer fifties) {
        this.fifties = fifties;
    }

    public Integer getCenturies() {
        return centuries;
    }

    public void setCenturies(Integer centuries) {
        this.centuries = centuries;
    }

    public Integer getMatches() {
        return matches;
    }

    public void setMatches(Integer matches) {
        this.matches = matches;
    }

    public Integer getHighest_score() {
        return highest_score;
    }

    public void setHighest_score(Integer highest_score) {
        this.highest_score = highest_score;
    }

    public Double getEconomy() {
        return economy;
    }

    public void setEconomy(Double economy) {
        this.economy = economy;
    }

    public Integer getWickets() {
        return wickets;
    }

    public void setWickets(Integer wickets) {
        this.wickets = wickets;
    }

    public Integer getFiveWicketHauls() {
        return fiveWicketHauls;
    }

    public void setFiveWicketHauls(Integer fiveWicketHauls) {
        this.fiveWicketHauls = fiveWicketHauls;
    }

    public Double getBowling_average() {
        return bowling_average;
    }

    public void setBowling_average(Double bowling_average) {
        this.bowling_average = bowling_average;
    }
}
