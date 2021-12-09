package nl.christine.websiteserver.service;

import nl.christine.websiteserver.model.Page;

public interface PageService {

    Page getPage(String site, String language, String page);
}
