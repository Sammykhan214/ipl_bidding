package com.Auction.Auction_website.Repository;

import com.Auction.Auction_website.Entity.PlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerStats_Repo extends JpaRepository<PlayerStats,Long> {
}
