package nl.christine.websiteserver.service;

import com.rometools.rome.io.FeedException;
import nl.christine.websiteserver.controller.request.CategoriesRequest;
import nl.christine.websiteserver.model.BlogEntry;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public interface BlogService {

    @Transactional
    BlogEntry getBlog(String site, String language);

    @Transactional
    BlogEntry getBlogForId(String site, String language, String id);

    BlogEntry getNext(String site, String language, long id);

    @Transactional
    void initBlog() throws FeedException, IOException;

    BlogEntry getPrevious(String site, String language, long l);

    List<BlogEntry> getBlogs(String site, String language, int count);

    @Transactional
    List<BlogEntry> getAllBlogs(String site, String language);

    BlogEntry edit(String site, String language, BlogEntry entry);

    List<BlogEntry> getBlogsForCategories(CategoriesRequest request);

    void deleteBlog(String id);
}
