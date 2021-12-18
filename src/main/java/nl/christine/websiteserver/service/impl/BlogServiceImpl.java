package nl.christine.websiteserver.service.impl;

import com.rometools.rome.io.FeedException;
import nl.christine.websiteserver.model.BlogEntry;
import nl.christine.websiteserver.dao.BlogDao;
import nl.christine.websiteserver.rome.RomeService;
import nl.christine.websiteserver.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

    @Autowired
    RomeService romeService;

    @Override
    public BlogEntry getBlog(String site, String language) {
        return blogDao.getBlog(site, language);
    }

    @Override
    public BlogEntry getNext(String site, String language, long id) {
        return blogDao.getNext(site, language, id);
    }

    @Override
    public void initBlog() throws FeedException, IOException {
        romeService.initBlog();
    }

    @Override
    public BlogEntry getPrevious(String site, String language, long id) {
        return blogDao.getPrevious(site, language, id);
    }
}
