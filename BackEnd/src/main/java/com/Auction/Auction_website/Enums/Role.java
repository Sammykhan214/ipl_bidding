package com.Auction.Auction_website.Enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    TEAM;
    @Override
    public String getAuthority() {
        return "ROLE_" + this.name(); // "ROLE_TEAM", "ROLE_ADMIN"
    }
}
