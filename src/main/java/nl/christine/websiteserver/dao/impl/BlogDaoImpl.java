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
                        + " a order by a._id desc ",
                BlogEntry.class);

        BlogEntry entry =  query.setMaxResults(1).getSingleResult();
        return entry;
    }

    @Override
    public BlogEntry getBlog(String site, String language, String id) {

        TypedQuery<BlogEntry> query = em.createQuery(
                "select a from " + BlogEntry.class.getSimpleName()
                        + " a where id = :id ",
                BlogEntry.class);

        BlogEntry entry =  query.setParameter("id", id).getSingleResult();
        return entry;
    }

    @Override
    public void insert(BlogEntry blogEntry) {
        em.persist(blogEntry);
    }

    @Override
    public BlogEntry getPrevious(String site, String language, long id) {
        BlogEntry entry =  getBlog(Math.max(0, id - 1));
        return entry;
    }

    @Override
    public BlogEntry getBlog(long id) {
        BlogEntry entry =  em.find(BlogEntry.class, id);
        return entry;
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
    public List<BlogEntry> getBlogs(String site, String language, int count) {
        TypedQuery<BlogEntry> query = em.createQuery(
                "select a from " + BlogEntry.class.getSimpleName()
                        + " a order by a.dateMillis desc ",
                BlogEntry.class);

        return query.setMaxResults(count).getResultList();
    }


    @Override
    public List<BlogEntry> getAllBlogs(String site, String language) {
        TypedQuery<BlogEntry> query = em.createQuery(
                "select a from " + BlogEntry.class.getSimpleName()
                        + " a order by a.dateMillis desc ",
                BlogEntry.class);

        return query.getResultList();
    }

    @Override
    public void clear() {
        em.createQuery("DELETE FROM " + BlogEntry.class.getSimpleName()).executeUpdate();
    }

}
