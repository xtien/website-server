package nl.christine.websiteserver.rome.impl;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import nl.christine.websiteserver.model.BlogEntry;
import nl.christine.websiteserver.dao.BlogDao;
import nl.christine.websiteserver.rome.RomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Component
public class RomeServiceImpl implements RomeService {

    Logger logger = LoggerFactory.getLogger(RomeServiceImpl.class);

    String fileName = "/home/christine/Documents/wordpress/christine.WordPress.2021-12-04.xml";

    @Autowired
    BlogDao blogDao;

    @Transactional
    @Override
    public void initBlog() throws IOException, FeedException {
        File feed = new File(fileName);
        final SyndFeedInput input = new SyndFeedInput();
        SyndFeed syndFeed = input.build(new XmlReader(feed.toURI().toURL()));

        blogDao.clear();

        syndFeed.getEntries().stream().forEach(e -> blogDao.insert(new BlogEntry(e)));
     }
}
