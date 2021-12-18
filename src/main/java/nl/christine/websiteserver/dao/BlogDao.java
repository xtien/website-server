package nl.christine.websiteserver.dao;

import nl.christine.websiteserver.model.BlogEntry;

public interface BlogDao {

    BlogEntry getBlog(String site, String language, String blogId);

    void insert(BlogEntry blogEntry);

    void clear();
}
