package nl.christine.websiteserver.dao;

import nl.christine.websiteserver.model.BlogEntry;

import java.util.List;

public interface BlogDao {

    BlogEntry getBlog(String site, String language);

    void insert(BlogEntry blogEntry);

    List<BlogEntry> getAllBlogs(String site, String language);

    List<BlogEntry> getBlogs(String site, String language, int count);

    void clear();

    BlogEntry getPrevious(String site, String language, long id);

    BlogEntry getBlog(long id);

    BlogEntry getNext(String site, String language, long id);

    BlogEntry getBlogForId(String site, String language, String id);

    List<BlogEntry> getPreviousList(String site, String language, long id);

    List<BlogEntry> getBlogsForCategories(String site, String language, List<String> categories);

    void deleteBlog(String id);
}
