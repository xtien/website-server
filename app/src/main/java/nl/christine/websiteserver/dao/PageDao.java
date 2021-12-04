package nl.christine.websiteserver.dao;

import nl.christine.websiteserver.model.Page;

public interface PageDao {

    Page getPage(String site,String language, String page);

    void persist(Page page);
}
