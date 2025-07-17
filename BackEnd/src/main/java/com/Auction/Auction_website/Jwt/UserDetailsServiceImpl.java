package com.Auction.Auction_website.Jwt;

import com.Auction.Auction_website.Entity.User;
import com.Auction.Auction_website.Repository.User_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    User_Repo user_repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = user_repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    return new org.springframework.security.core.userdetails.User(
            user.getEmail(),user.getPassword(),
            Collections.singleton(new SimpleGrantedAuthority("ROLE"+user.getRole()))
    );
    }
}
