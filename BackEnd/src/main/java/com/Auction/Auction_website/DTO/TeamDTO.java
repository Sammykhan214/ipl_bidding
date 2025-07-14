package com.Auction.Auction_website.DTO;

import com.Auction.Auction_website.Entity.Bid;
import com.Auction.Auction_website.Entity.Team;

public class TeamDTO {
    private Long id;
    private String name;

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
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
}

