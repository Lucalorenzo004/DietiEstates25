package com.backend.user.model;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    MANAGER,
    AGENT;


    @Override
    public String getAuthority() {
        return "ROLE_"+name();
    }
}
