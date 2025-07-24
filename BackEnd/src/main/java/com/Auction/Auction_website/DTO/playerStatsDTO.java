package com.Auction.Auction_website.DTO;

import com.opencsv.bean.CsvBindByName;

public class playerStatsDTO {
    @CsvBindByName(column = "Player_Name")
    private String name;
    @CsvBindByName(column = "Runs_Scored")
    private String runs;
    @CsvBindByName(column = "Batting_Average")
    private String batting_average;
    @CsvBindByName(column = "Batting_Strike_Rate")
    private String strikeRate;
    @CsvBindByName(column = "Half_Centuries")
    private String fifties;
    @CsvBindByName(column = "Centuries")
    private String centuries;
    @CsvBindByName(column = "Matches_Batted")
    private String batting_matches;
    @CsvBindByName(column = "Highest_Score")
    private String highest_score;
    //bowling
    @CsvBindByName(column = "Economy_Rate")
    private String economy;
    @CsvBindByName(column = "Wickets_Taken")
    private String wickets;
    @CsvBindByName(column = "Five_Wicket_Hauls")
    private String fiveWicketHauls;
    @CsvBindByName(column = "Bowling_Average")
    private String bowling_average;
//    @CsvBindByName(column = "Matches_Bowled")
//    private String best_bowling;
    @CsvBindByName(column = "Matches_Bowled")
    private String bowling_matches;



//    public String getBest_bowling() {
//        return best_bowling;
//    }
//
//    public void setBest_bowling(String best_bowling) {
//        this.best_bowling = best_bowling;
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRuns() {
        return runs;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }

    public String getBatting_average() {
        return batting_average;
    }

    public void setBatting_average(String batting_average) {
        this.batting_average = batting_average;
    }

    public String getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(String strikeRate) {
        this.strikeRate = strikeRate;
    }

    public String getFifties() {
        return fifties;
    }

    public void setFifties(String fifties) {
        this.fifties = fifties;
    }

    public String getCenturies() {
        return centuries;
    }

    public void setCenturies(String centuries) {
        this.centuries = centuries;
    }

    public String getBatting_matches() {
        return batting_matches;
    }

    public void setBatting_matches(String batting_matches) {
        this.batting_matches = batting_matches;
    }

    public String getHighest_score() {
        return highest_score;
    }

    public void setHighest_score(String highest_score) {
        this.highest_score = highest_score;
    }

    public String getEconomy() {
        return economy;
    }

    public void setEconomy(String economy) {
        this.economy = economy;
    }

    public String getWickets() {
        return wickets;
    }

    public void setWickets(String wickets) {
        this.wickets = wickets;
    }

    public String getFiveWicketHauls() {
        return fiveWicketHauls;
    }

    public void setFiveWicketHauls(String fiveWicketHauls) {
        this.fiveWicketHauls = fiveWicketHauls;
    }

    public String getBowling_average() {
        return bowling_average;
    }

    public void setBowling_average(String bowling_average) {
        this.bowling_average = bowling_average;
    }

    public String getBowling_matches() {
        return bowling_matches;
    }

    public void setBowling_matches(String bowling_matches) {
        this.bowling_matches = bowling_matches;
    }
}
