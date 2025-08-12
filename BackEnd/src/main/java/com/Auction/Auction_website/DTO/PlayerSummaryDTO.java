package com.Auction.Auction_website.DTO;

import com.Auction.Auction_website.Entity.Player;

public class PlayerSummaryDTO {
    private String name;
    private String role;
    private Double soldPrice;

    public PlayerSummaryDTO(PlayerMiniDTO player) {
        this.name = player.getName();
        this.role = player.getRole();
        this.soldPrice = player.getSoldPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(Double soldPrice) {
        this.soldPrice = soldPrice;
    }
}
