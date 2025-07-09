package com.Auction.Auction_website.Repository;

import com.Auction.Auction_website.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Player_Repo extends JpaRepository<Player,Long> {
}
