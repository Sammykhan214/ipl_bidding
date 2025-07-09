package com.Auction.Auction_website.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "players")

@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String role; // e.g. Batsman, Bowler, All-rounder

    private Double basePrice;

    private Double soldPrice;

    // 🔗 Many players can belong to one team
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team; // team can be null if unsold

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
