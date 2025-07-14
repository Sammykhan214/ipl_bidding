package com.Auction.Auction_website.Repository;

import com.Auction.Auction_website.Entity.Team;
import com.Auction.Auction_website.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Team_Repo extends JpaRepository<Team,Long> {
}
