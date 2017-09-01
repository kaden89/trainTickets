package com.dkarachurin.trainTickets.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Denis Karachurin on 01.09.2017.
 */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

}
