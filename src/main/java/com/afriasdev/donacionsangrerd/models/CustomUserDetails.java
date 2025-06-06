package com.afriasdev.donacionsangrerd.models;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final String name;
    private final String lastname;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String username, String password, String name, String lastname, String email, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.authorities = authorities;
    }
}
