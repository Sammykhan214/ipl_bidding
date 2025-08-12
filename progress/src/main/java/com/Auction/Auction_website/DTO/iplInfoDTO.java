package com.Auction.Auction_website.DTO;

import com.opencsv.bean.CsvBindByName;

public class iplInfoDTO {
    @CsvBindByName(column = "Name")
    private String name;
    @CsvBindByName(column = "Nationality")
    private String nationality;
    @CsvBindByName(column = "BattingStyle")
    private String battingStyle;
    @CsvBindByName(column = "BowlingStyle")
    private String bowlingStyle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBattingStyle() {
        return battingStyle;
    }

    public void setBattingStyle(String battingStyle) {
        this.battingStyle = battingStyle;
    }

    public String getBowlingStyle() {
        return bowlingStyle;
    }

    public void setBowlingStyle(String bowlingStyle) {
        this.bowlingStyle = bowlingStyle;
    }
}
