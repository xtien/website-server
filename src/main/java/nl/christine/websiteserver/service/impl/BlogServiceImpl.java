package nl.christine.websiteserver.service.impl;

import com.rometools.rome.io.FeedException;
import nl.christine.websiteserver.dao.BlogDao;
import nl.christine.websiteserver.model.BlogEntry;
import nl.christine.websiteserver.rome.RomeService;
import nl.christine.websiteserver.service.BlogService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.io.IOException;
import java.util.List;

@Component
public class BlogServiceImpl implements BlogService {

    @Transient
    String dateFormat = "yyyy-MM-dd hh:mm:ss";

    @Autowired
    BlogDao blogDao;

    @Autowired
    RomeService romeService;

    @Override
    @Transactional
    public BlogEntry getBlog(String site, String language) {
        return blogDao.getBlog(site, language);
    }

    @Override
    @Transactional
    public BlogEntry getBlog(String site, String language, String id) {
        return blogDao.getBlog(site, language, id);
    }

    @Override
    @Transactional
    public BlogEntry getNext(String site, String language, long id) {
        return blogDao.getNext(site, language, id);
    }

    @Override
    @Transactional
    public BlogEntry getPrevious(String site, String language, long id) {
        return blogDao.getPrevious(site, language, id);
    }

    @Override
    @Transactional
    public List<BlogEntry> getBlogs(String site, String language, int count) {
        return blogDao.getBlogs(site, language, count);
    }

    @Override
    @Transactional
    public List<BlogEntry> getAllBlogs(String site, String language) {
        return blogDao.getAllBlogs(site, language);
    }

    @Override
    @Transactional
    public BlogEntry edit(String site, String language, BlogEntry entry) {
        if (StringUtils.isEmpty(entry.getId())) {
            entry.setDate(System.currentTimeMillis());
            entry.setDateString(FastDateFormat.getInstance(dateFormat).format(entry.getDateMillis()));
            blogDao.insert(entry);
            entry.setId(Long.toString(entry.get_id()));
            return entry;
        } else {
            BlogEntry existingEntry = blogDao.getBlog(Integer.parseInt(entry.getId()));
            if (StringUtils.isNotEmpty(entry.getTitle())) {
                existingEntry.setTitle(entry.getTitle());
            }
            if (StringUtils.isNotEmpty(entry.getText())) {
                existingEntry.setText(entry.getText());
            }
            return existingEntry;
        }
    }

    @Override
    @Transactional
    public void initBlog() throws FeedException, IOException {
        romeService.initBlog();
    }
}
