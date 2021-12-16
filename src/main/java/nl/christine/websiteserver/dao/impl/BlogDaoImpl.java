package nl.christine.websiteserver.dao.impl;

import nl.christine.websiteserver.blog.BlogEntry;
import nl.christine.websiteserver.dao.BlogDao;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class BlogDaoImpl implements BlogDao {

    @PersistenceContext(unitName = "defaultPU")
    private EntityManager em;

    @Override
    public BlogEntry getBlog(String site, String language, String blogId) {
        return null;
    }
}
