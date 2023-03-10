package com.example.oauth2.security.domain;

import com.example.oauth2.member.domain.AuthProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserPrincipal extends User implements UserProvider {

    public UserPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    @Override
    public AuthProvider getProvider() {
        return AuthProvider.LOCAL;
    }
}
