package nl.christine.websiteserver.rome.impl;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import nl.christine.websiteserver.rome.RomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class RomeServiceImpl implements RomeService {

    Logger logger = LoggerFactory.getLogger(RomeServiceImpl.class);

    String fileName = "/home/christine/Documents/wordpress/christine.WordPress.2021-12-04.xml";

    @PostConstruct
    public void init() throws IOException, FeedException {

        File feed = new File(fileName);
        final SyndFeedInput input = new SyndFeedInput();
        SyndFeed syndFeed = input.build(new XmlReader(feed.toURI().toURL()));

        SyndEntry entry1 = syndFeed.getEntries().get(100);
        logger.debug("xtien " + entry1.getContents().get(0).getType());
        logger.debug("xtien " + entry1.getContents().get(0).getMode());
        logger.debug("xtien " + entry1.getContents().get(0).getValue());
    }
}
