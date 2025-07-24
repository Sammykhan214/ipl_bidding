package com.Auction.Auction_website.Repository;

import com.Auction.Auction_website.Entity.Player;
import com.Auction.Auction_website.Enums.AuctionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Player_Repo extends JpaRepository<Player,Long> {
      List<Player> findByStatus(AuctionStatus status);
      boolean existsByStatus(AuctionStatus status);
      Optional<Player> findByName(String name);

}
