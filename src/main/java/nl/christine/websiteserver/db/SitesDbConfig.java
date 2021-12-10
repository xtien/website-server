/*
 * Copyright (c) 2018 - 2021, Zaphod Consulting BV, Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of
 * the Apache License, Version 2.0. You can find a copy of the license at
 * http://www.apache.org/licenses/LICENSE-2.0.
 */

package nl.christine.websiteserver.db;

import nl.christine.websiteserver.properties.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("!test")
public class SitesDbConfig {

    @Value("${spring.datasource.url}")
    private String url;

    private final String userName;

    private final String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Autowired
    public SitesDbConfig(MyProperties properties) {
        password = properties.getProperty("dbpassword");
        userName = properties.getProperty("dbusername");
    }

    @Bean(name = "datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(driverClass)
                .url(url)
                .username(userName)
                .password(password)
                .build();
    }
}
