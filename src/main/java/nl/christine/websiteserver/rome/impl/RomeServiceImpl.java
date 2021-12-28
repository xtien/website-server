package nl.christine.websiteserver.rome.impl;

import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import nl.christine.websiteserver.dao.BlogDao;
import nl.christine.websiteserver.model.BlogEntry;
import nl.christine.websiteserver.properties.MyProperties;
import nl.christine.websiteserver.rome.RomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;

@Component
public class RomeServiceImpl implements RomeService {

    String fileName;

    @Autowired
    private MyProperties properties;

    @Autowired
    BlogDao blogDao;

    @PostConstruct
    public void init() {
        fileName = properties.getProperty("wordpressexportfile");
     }

    @Transactional
    @Override
    public void initBlog() throws IOException, FeedException {
        blogDao.clear();

        new SyndFeedInput()
                .build(new XmlReader(new File(fileName).toURI().toURL()))
                .getEntries()
                .stream()
                .map(BlogEntry::new)
                .sorted(Comparator.comparingLong(BlogEntry::getDateMillis))
                .forEach(e -> {
                    blogDao.insert(e);
                    e.setId(Long.toString(e.get_id()));
                });
    }
}




