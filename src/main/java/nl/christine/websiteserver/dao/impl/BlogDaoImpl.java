package nl.christine.websiteserver.dao.impl;

import nl.christine.websiteserver.model.BlogEntry;
import nl.christine.websiteserver.dao.BlogDao;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class BlogDaoImpl implements BlogDao {

    @PersistenceContext(unitName = "defaultPU")
    private EntityManager em;

    @Override
    public BlogEntry getBlog(String site, String language) {

        TypedQuery<BlogEntry> query = em.createQuery(
                "select a from " + BlogEntry.class.getSimpleName()
                        + " a order by a.dateMillis desc limit 1",
                BlogEntry.class);

        return query.getSingleResult();
    }

    @Override
    public void insert(BlogEntry blogEntry) {
        em.persist(blogEntry);

    }

    @Override
    public BlogEntry getPrevious(String site, String language, long id) {

        return null;
    }

    @Override
    public BlogEntry getNext(String site, String language, long id) {
        return null;
    }

    @Override
    public void clear() {
        em.createQuery("DELETE FROM " + BlogEntry.class.getSimpleName()).executeUpdate();
    }

}
