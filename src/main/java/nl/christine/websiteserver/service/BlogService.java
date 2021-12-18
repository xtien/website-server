package nl.christine.websiteserver.service;

import com.rometools.rome.io.FeedException;
import nl.christine.websiteserver.model.BlogEntry;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public interface BlogService {

    @Transactional
    BlogEntry getBlog(String site, String language);

    @Transactional
    BlogEntry getBlog(String site, String language, String id);

    BlogEntry getNext(String site, String language, long id);

    @Transactional
    void initBlog() throws FeedException, IOException;

    BlogEntry getPrevious(String site, String language, long l);

    List<BlogEntry> getBlogs(String site, String language, int count);

    List<BlogEntry> getBlogs(String site, String language, long id, int count);

    BlogEntry edit(String site, String language, BlogEntry entry);
}
