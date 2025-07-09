package com.Auction.Auction_website.DTO;

import lombok.Getter;
import lombok.Setter;


public class BidRequest {
    private Long playerId;
    private Long teamId;
    private Double amount;

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
