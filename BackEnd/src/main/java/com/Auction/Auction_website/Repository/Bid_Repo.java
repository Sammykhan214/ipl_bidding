package com.Auction.Auction_website.Repository;

import com.Auction.Auction_website.Entity.Bid;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface Bid_Repo  extends JpaRepository<Bid,Long> {
}
