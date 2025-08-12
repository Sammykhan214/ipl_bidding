package com.Auction.Auction_website.DTO;

import com.Auction.Auction_website.Entity.Bid;


import java.time.LocalDateTime;

public class BidDTO {
        private Long id;
        private Double amount;
        private String teamName;
        private LocalDateTime time;

        public BidDTO(Bid bid) {
            this.id = bid.getId();
            this.amount = bid.getAmount();
            this.time = bid.getTime();
            this.teamName = bid.getTeam().getName();
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}


