package nl.christine.websiteserver.service.impl;

import nl.christine.websiteserver.blog.BlogEntry;
import nl.christine.websiteserver.dao.BlogDao;
import nl.christine.websiteserver.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

    @Override
    public BlogEntry getBlog(String site, String language, String blogId) {
        return blogDao.getBlog(site,language,blogId);
    }
}
