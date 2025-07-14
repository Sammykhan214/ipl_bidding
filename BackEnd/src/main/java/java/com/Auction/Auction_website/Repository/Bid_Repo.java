package com.Auction.Auction_website.Repository;

import com.Auction.Auction_website.Entity.Bid;
import com.Auction.Auction_website.Entity.Player;
import lombok.extern.java.Log;
import org.hibernate.mapping.Selectable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface Bid_Repo  extends JpaRepository<Bid,Long> {
        List<Bid> findByPlayer(Player player);
        Bid findTopByPlayerOrderByAmountDesc(Player player); // highest bid

}
