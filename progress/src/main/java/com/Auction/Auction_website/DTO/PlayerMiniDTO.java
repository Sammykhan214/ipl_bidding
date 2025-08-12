package com.Auction.Auction_website.DTO;

import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Entity.PlayerStats;
import com.Auction.Auction_website.Enums.AuctionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class PlayerMiniDTO {
    private Long id;
    private String name;
    private String role;
    private String nationality;
    private Double basePrice;
    private Double soldPrice;
    private AuctionStatus status;
private String battingStyle;
private String bowlingStyle;
private PlayerStatsResponseDTO playerStats;
    public PlayerMiniDTO(Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.role = player.getRole();
        this.nationality = player.getNationality();
        this.basePrice = player.getBasePrice();
        this.soldPrice = player.getSoldPrice();
        this.status = player.getStatus();
        this.battingStyle=player.getBattingStyle();
        this.bowlingStyle=player.getBowlingStyle();
        if(player.getPlayerStats()!=null)
        {
            this.playerStats = new PlayerStatsResponseDTO();
            this.playerStats.setRuns(player.getPlayerStats().getRuns());
            this.playerStats.setBatting_average(player.getPlayerStats().getBatting_average());
            this.playerStats.setStrikeRate(player.getPlayerStats().getStrikeRate());
            this.playerStats.setFifties(player.getPlayerStats().getFifties());
            this.playerStats.setCenturies(player.getPlayerStats().getCenturies());
            this.playerStats.setMatches(player.getPlayerStats().getMatches());
            this.playerStats.setHighest_score(player.getPlayerStats().getHighest_score());

            this.playerStats.setEconomy(player.getPlayerStats().getEconomy());
            this.playerStats.setWickets(player.getPlayerStats().getWickets());
            this.playerStats.setFiveWicketHauls(player.getPlayerStats().getFiveWicketHauls());
            this.playerStats.setBowling_average(player.getPlayerStats().getBowling_average());
        }

    }
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

    public AuctionStatus getStatus() {
        return status;
    }

    public void setStatus(AuctionStatus status) {
        this.status = status;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Double getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(Double soldPrice) {
        this.soldPrice = soldPrice;
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

    public PlayerStatsResponseDTO getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(PlayerStatsResponseDTO playerStats) {
        this.playerStats = playerStats;
    }
}

