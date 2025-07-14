package com.Auction.Auction_website.DTO;

import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Enums.AuctionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerFullDTO {
        private Long id;
        private String name;
        private String role;
        private String nationality;
        private Double basePrice;
        private Double soldPrice;
        private AuctionStatus status;
        private TeamDTO team;


        public PlayerFullDTO(Player player) {
            this.id = player.getId();
            this.name = player.getName();
            this.role = player.getRole();
            this.nationality = player.getNationality();
            this.basePrice = player.getBasePrice();
            this.status = player.getStatus();
            if(status==AuctionStatus.SOLD&&player.getTeam()!=null){
            this.team = new TeamDTO(player.getTeam());
            this.soldPrice = player.getSoldPrice();}
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
