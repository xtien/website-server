package nl.christine.websiteserver.dao;

import nl.christine.websiteserver.model.BlogEntry;

public interface BlogDao {

    BlogEntry getBlog(String site, String language);

    void insert(BlogEntry blogEntry);

    void clear();

    BlogEntry getPrevious(String site, String language, long id);

    BlogEntry getNext(String site, String language, long id);
}
