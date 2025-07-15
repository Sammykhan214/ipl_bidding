package com.Auction.Auction_website.Service.Impl;

import com.Auction.Auction_website.DTO.TeamDTO;
import com.Auction.Auction_website.DTO.TeamSummaryDTO;
import com.Auction.Auction_website.DTO.TeamwithPlayersDTO;
import com.Auction.Auction_website.Entity.Team;
import com.Auction.Auction_website.Repository.TeamRepo;
import com.Auction.Auction_website.Repository.Team_Repo;
import com.Auction.Auction_website.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamRepo team_repo;
    @Override
    public List<TeamDTO> getAllTeams() {
        List<TeamDTO>teams=team_repo.findAll()
                .stream().map(team->new TeamDTO(team)).toList();
        return teams;
    }

    @Override
    public TeamDTO getTeamById(Long TeamId) {
        Team team = team_repo.findById(TeamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        return new TeamDTO(team);
    }

    @Override
    public TeamwithPlayersDTO getTeamWithPlayers(Long TeamId) {
        Team team = team_repo.findById(TeamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        return new TeamwithPlayersDTO(team);
    }

    @Override
    public TeamSummaryDTO getTeamSummary(Long TeamId) {
        Team team = team_repo.findById(TeamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        TeamwithPlayersDTO teamPlayers=new TeamwithPlayersDTO(team);
        return new TeamSummaryDTO(teamPlayers);
    }
}
