/*
 * Copyright (c) 2018 - 2021, Zaphod Consulting BV, Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of
 * the Apache License, Version 2.0. You can find a copy of the license at
 * http://www.apache.org/licenses/LICENSE-2.0.
 */

package nl.christine.websiteserver.security.model;

import java.util.Collection;
import java.util.LinkedList;

public class Role {

    private String name;
    private Collection<Privilege> privileges = new LinkedList<>();

    public Role(String name) {
        this.name = name;
    }


    public Collection<Privilege> getPrivileges() {
        return privileges;
    }
}
