package nl.christine.websiteserver.service;

import com.rometools.rome.io.FeedException;
import nl.christine.websiteserver.model.BlogEntry;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

public interface BlogService {

    @Transactional
    BlogEntry getBlog(String site, String language, String blogId);

    @Transactional
    void initBlog() throws FeedException, IOException;
}
