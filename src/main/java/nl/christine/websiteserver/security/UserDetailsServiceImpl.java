/*
 * Copyright (c) 2018 - 2021, Zaphod Consulting BV, Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of
 * the Apache License, Version 2.0. You can find a copy of the license at
 * http://www.apache.org/licenses/LICENSE-2.0.
 */

package nl.christine.websiteserver.security;

import nl.christine.websiteserver.properties.MyProperties;
import nl.christine.websiteserver.security.model.Privilege;
import nl.christine.websiteserver.security.model.Role;
import nl.christine.websiteserver.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service("userDetailsService")
@Profile("!test")
public class UserDetailsServiceImpl implements UserDetailsService {

    private String userName;
    private String userPassword;
    private User user = new User();

    @Autowired
    MyProperties properties;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

        userName = properties.getProperty("security_user");
        userPassword = passwordEncoder.encode(properties.getProperty("security_password"));

        Collection<Role> roles = new LinkedList<>();
        Role role = new Role("ROLE_USER");
        role.getPrivileges().add(new Privilege("WRITE_PRIVILEGE"));
        roles.add(role);
        user.setRoles(roles);
    }

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        if (userName.equals(this.userName)) {
            return new org.springframework.security.core.userdetails.User(
                    userName, userPassword, true, true, true,
                    true, getAuthorities(user.getRoles()));
        } else {
            throw new UsernameNotFoundException("user " + userName + " not found");
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
