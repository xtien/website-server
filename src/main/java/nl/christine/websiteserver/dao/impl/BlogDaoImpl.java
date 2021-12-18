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
                        + " a order by a.id desc ",
                BlogEntry.class);

        return query.setMaxResults(1).getSingleResult();
    }

    @Override
    public void insert(BlogEntry blogEntry) {
        em.persist(blogEntry);

    }

    @Override
    public BlogEntry getPrevious(String site, String language, long id) {

        return getBlog(Math.max(0, id));
    }

    @Override
    public BlogEntry getBlog(long id) {
        return em.find(BlogEntry.class, id);
    }

    @Override
    public BlogEntry getNext(String site, String language, long id) {
        BlogEntry blogEntry = getBlog(id + 1);
        if (blogEntry == null) {
            blogEntry = getBlog(id);
        }
        return blogEntry;
    }

    @Override
    public List<BlogEntry> getBlogs(String site, String language, long id, int count) {
        TypedQuery<BlogEntry> query = em.createQuery(
                "select a from " + BlogEntry.class.getSimpleName()
                        + " a order by a.id desc ",
                BlogEntry.class);

        return query.setMaxResults(count).getResultList();
    }

    @Override
    public List<BlogEntry> getBlogs(String site, String language, int count) {
        TypedQuery<BlogEntry> query = em.createQuery(
                "select a from " + BlogEntry.class.getSimpleName()
                        + " a order by a.id desc ",
                BlogEntry.class);

        return query.setMaxResults(count).getResultList();
    }

    @Override
    public void clear() {
        em.createQuery("DELETE FROM " + BlogEntry.class.getSimpleName()).executeUpdate();
    }

}
