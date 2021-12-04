/*
 * Copyright (c) 2018 - 2021, Zaphod Consulting BV, Christine Karman
 * This project is free software: you can redistribute it and/or modify it under the terms of
 * the Apache License, Version 2.0. You can find a copy of the license at
 * http://www.apache.org/licenses/LICENSE-2.0.
 */

package nl.christine.websiteserver.service.impl;

import nl.christine.websiteserver.properties.MyProperties;
import nl.christine.websiteserver.service.TextFileService;
import nl.christine.websiteserver.text.TextReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("textFileService")
public class TextFileServiceImpl implements TextFileService {

    @Value("${defaultlanguage}")
    private String defaultLanguage;

    @Autowired
    private MyProperties properties;

    @Autowired
    private TextReader textReader;

    private String fileDirectory;
    
    @PostConstruct
    public void init() {
        fileDirectory = properties.getProperty("pages_directory");
        if(fileDirectory.endsWith("/")){
            fileDirectory = fileDirectory.substring(0, fileDirectory.length()-1);
        }
    }

    @Override
    public String getText(String site, String language, String page) {
        return getText("txt", site, language, page);
    }

    @Override
    public String getTitle(String site, String language, String page) {
        return getText("title", site, language, page);
    }

    private String getText(String type, String site, String language, String page) {
        String fileName = fileDirectory + "/" + site + "/" + language + "/" + page + "." + type;
        String result = textReader.getText(fileName);
        if (result == null) {
            fileName = fileDirectory + "/" + site + "/" + defaultLanguage + "/" + page + "." + type;
            result = textReader.getText(fileName);
        }
        if (result == null) {
            result = "text file not found";
        }
        return result;
    }
}
