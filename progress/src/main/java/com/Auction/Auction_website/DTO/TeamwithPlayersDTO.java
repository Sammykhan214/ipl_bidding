package com.Auction.Auction_website.DTO;

import com.Auction.Auction_website.Entity.Team;

import java.util.List;
import java.util.stream.Collectors;

public class TeamwithPlayersDTO {
    private Long id;
    private String name;
    private Double budget;
    private List<PlayerMiniDTO> players;
    public TeamwithPlayersDTO(Team team){
        this.id = team.getId();
        this.name = team.getName();
        this.budget = team.getBudget();
        this.players=team.getPlayers()
                .stream().map(players->new PlayerMiniDTO(players))
                .collect(Collectors.toList());
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

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public List<PlayerMiniDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerMiniDTO> players) {
        this.players = players;
    }
}
