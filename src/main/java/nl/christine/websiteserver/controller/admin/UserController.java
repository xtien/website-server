/*
 * Copyright (c) 2018 - 2021, Zaphod Consulting BV, Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of
 * the Apache License, Version 2.0. You can find a copy of the license at
 * http://www.apache.org/licenses/LICENSE-2.0.
 */

package nl.christine.websiteserver.controller.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import nl.christine.websiteserver.controller.request.LogoutRequest;
import nl.christine.websiteserver.controller.result.LoginResult;
import nl.christine.websiteserver.controller.result.LogoutResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "login", description = "")public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping(value = "/login")
    public ResponseEntity<LoginResult> login() {

        HttpStatus status = HttpStatus.OK;
        LoginResult loginResult = new LoginResult();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        UserDetails user = userDetailsService.loadUserByUsername(currentPrincipalName);
        loginResult.setAuthorities(user.getAuthorities());

        return new ResponseEntity<>(loginResult, status);
    }

    @PostMapping(value = "/user/signout")
    public ResponseEntity<LogoutResult> logout(@RequestBody LogoutRequest request) {

        HttpStatus status = HttpStatus.OK;
        LogoutResult logoutResult = new LogoutResult();
        return new ResponseEntity<>(logoutResult, status);
    }
}
