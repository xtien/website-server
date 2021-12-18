package nl.christine.websiteserver.service;

import com.rometools.rome.io.FeedException;
import nl.christine.websiteserver.model.BlogEntry;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

public interface BlogService {

    @Transactional
    BlogEntry getBlog(String site, String language);

    BlogEntry getNext(String site, String language, long id);

    @Transactional
    void initBlog() throws FeedException, IOException;

    BlogEntry getPrevious(String site, String language, long l);
}
