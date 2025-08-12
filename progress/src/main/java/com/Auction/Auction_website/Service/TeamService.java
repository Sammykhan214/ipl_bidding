package com.Auction.Auction_website.Service;

import com.Auction.Auction_website.DTO.TeamDTO;
import com.Auction.Auction_website.DTO.TeamSummaryDTO;
import com.Auction.Auction_website.DTO.TeamwithPlayersDTO;

import java.util.List;


public interface TeamService {
    List<TeamDTO> getAllTeams();
    TeamDTO getTeamById(Long id);
    TeamwithPlayersDTO getTeamWithPlayers(Long id);
    TeamSummaryDTO getTeamSummary(Long TeamId);
}
