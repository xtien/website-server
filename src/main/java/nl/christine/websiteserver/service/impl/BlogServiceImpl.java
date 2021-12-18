package nl.christine.websiteserver.service.impl;

import com.rometools.rome.io.FeedException;
import nl.christine.websiteserver.model.BlogEntry;
import nl.christine.websiteserver.dao.BlogDao;
import nl.christine.websiteserver.rome.RomeService;
import nl.christine.websiteserver.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

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
    public BlogEntry getPrevious(String site, String language, long id) {
        return blogDao.getPrevious(site, language, id);
    }

    @Override
    public List<BlogEntry> getBlogs(String site, String language, int count) {
        return blogDao.getBlogs(site,language, count);
    }

    @Override
    public List<BlogEntry> getBlogs(String site, String language, long id, int count) {
        return blogDao.getBlogs(site,language, id, count);
    }

    @Override
    public void initBlog() throws FeedException, IOException {
        romeService.initBlog();
    }
}
