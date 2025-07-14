package com.Auction.Auction_website.DTO;

import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Enums.AuctionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class PlayerMiniDTO {
    private Long id;
    private String name;
    private AuctionStatus status;
    private TeamDTO team;

    public PlayerMiniDTO(Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.status = player.getStatus();
        if(status==AuctionStatus.SOLD&&player.getTeam()!=null)
        this.team = new TeamDTO(player.getTeam());
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

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }
}

