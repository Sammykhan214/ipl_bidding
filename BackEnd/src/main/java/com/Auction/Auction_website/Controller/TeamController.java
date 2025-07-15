package com.Auction.Auction_website.Controller;

import com.Auction.Auction_website.DTO.TeamDTO;
import com.Auction.Auction_website.DTO.TeamSummaryDTO;
import com.Auction.Auction_website.DTO.TeamwithPlayersDTO;
import com.Auction.Auction_website.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auction/teams")
public class TeamController {
@Autowired
private  TeamService team_service;

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        return ResponseEntity.ok(team_service.getAllTeams());
    }

    @GetMapping("/{TeamId}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long TeamId) {
        return ResponseEntity.ok(team_service.getTeamById(TeamId));
    }

    @GetMapping("/{id}/players")
    public ResponseEntity<TeamwithPlayersDTO> getTeamWithPlayers(@PathVariable Long id) {
        return ResponseEntity.ok(team_service.getTeamWithPlayers(id));
    }

    @GetMapping("/summary/{id}")
    public ResponseEntity<TeamSummaryDTO> getTeamSummary(@PathVariable Long id) {
        return ResponseEntity.ok(team_service.getTeamSummary(id));
    }
}
