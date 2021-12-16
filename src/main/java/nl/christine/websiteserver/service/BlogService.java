package nl.christine.websiteserver.service;

import nl.christine.websiteserver.blog.BlogEntry;
import nl.christine.websiteserver.model.Page;
import org.springframework.transaction.annotation.Transactional;

public interface BlogService {

    @Transactional
    BlogEntry getBlog(String site, String language, String blogId);
}
