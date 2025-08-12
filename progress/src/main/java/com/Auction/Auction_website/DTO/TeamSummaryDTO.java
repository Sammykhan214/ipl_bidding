package com.Auction.Auction_website.DTO;

import java.util.List;

public class TeamSummaryDTO {
    private Long Id;
    private int playersBought;
    private Double BudgetLeft;
    private String name;
    private List<PlayerSummaryDTO>players;

    public TeamSummaryDTO(TeamwithPlayersDTO team) {
        this.Id = team.getId();
        this.playersBought = team.getPlayers().size();
        BudgetLeft = team.getBudget();
        this.name = team.getName();
        this.players=team.getPlayers().stream()
                .map(player->new PlayerSummaryDTO(player))
                .toList();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getPlayersBought() {
        return playersBought;
    }

    public void setPlayersBought(int playersBought) {
        this.playersBought = playersBought;
    }

    public List<PlayerSummaryDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerSummaryDTO> players) {
        this.players = players;
    }

    public Double getBudgetLeft() {
        return BudgetLeft;
    }

    public void setBudgetLeft(Double budgetLeft) {
        BudgetLeft = budgetLeft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
