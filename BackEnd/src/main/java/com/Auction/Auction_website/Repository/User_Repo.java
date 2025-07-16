package com.Auction.Auction_website.Repository;

import com.Auction.Auction_website.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface User_Repo extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
