package nl.christine.websiteserver.dao;

import nl.christine.websiteserver.blog.BlogEntry;

public interface BlogDao {

    BlogEntry getBlog(String site, String language, String blogId);
}
