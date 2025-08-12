package com.Auction.Auction_website.Service.Impl;

import com.Auction.Auction_website.Entity.Team;
import com.Auction.Auction_website.Entity.User;
import com.Auction.Auction_website.Enums.Role;
import com.Auction.Auction_website.Repository.Team_Repo;
import com.Auction.Auction_website.Repository.User_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    Team_Repo team_repo;
    @Autowired
    PasswordEncoder passwordEncoder;
@Autowired
    User_Repo user_repo;
    public User register(User req) {
        // Team count check
        if (req.getRole() == Role.TEAM) {
            long teamCount = team_repo.count();
            if (teamCount >= 10) {
                throw new RuntimeException("❌ Maximum team limit reached (10). Try again later or register as viewer.");
            }
        }

        // proceed with creating user
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(req.getRole());


        if (user.getRole().equals(Role.TEAM)) {
            Team team = new Team();
            team.setName(user.getName());
            team.setBudget(100000000.00);
            team.setUser(user);
            team_repo.save(team);
        }

        return user_repo.save(user);
    }
}
