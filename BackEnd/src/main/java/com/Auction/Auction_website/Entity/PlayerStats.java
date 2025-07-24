package com.Auction.Auction_website.Entity;

import jakarta.persistence.*;

@Entity
public class PlayerStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int runs;
    private double batting_average;
    private double strikeRate;
    private int fifties;
    private int centuries;
    private int matches;
    private int highest_score;
    //bowling
    private double economy;
    private int wickets;
    private int fiveWicketHauls;
    private Double bowling_average;

@OneToOne
@JoinColumn(name = "player_id")
Player player;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public double getBatting_average() {
        return batting_average;
    }

    public void setBatting_average(double batting_average) {
        this.batting_average = batting_average;
    }

    public double getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(double strikeRate) {
        this.strikeRate = strikeRate;
    }

    public int getFifties() {
        return fifties;
    }

    public void setFifties(int fifties) {
        this.fifties = fifties;
    }

    public int getCenturies() {
        return centuries;
    }

    public void setCenturies(int centuries) {
        this.centuries = centuries;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getHighest_score() {
        return highest_score;
    }

    public void setHighest_score(int highest_score) {
        this.highest_score = highest_score;
    }

    public double getEconomy() {
        return economy;
    }

    public void setEconomy(double economy) {
        this.economy = economy;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getFiveWicketHauls() {
        return fiveWicketHauls;
    }

    public void setFiveWicketHauls(int fiveWicketHauls) {
        this.fiveWicketHauls = fiveWicketHauls;
    }

    public Double getBowling_average() {
        return bowling_average;
    }

    public void setBowling_average(Double bowling_average) {
        this.bowling_average = bowling_average;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

