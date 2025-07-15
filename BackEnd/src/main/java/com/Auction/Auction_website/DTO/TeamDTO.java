package com.Auction.Auction_website.DTO;

import com.Auction.Auction_website.Entity.Bid;
import com.Auction.Auction_website.Entity.Team;

public class TeamDTO {
    private Long id;
    private String name;
    private Double budget;

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.budget=team.getBudget();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
public Double getBudget(){
        return budget;
}
public void setBudget(Double budget){
        this.budget=budget;
}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

