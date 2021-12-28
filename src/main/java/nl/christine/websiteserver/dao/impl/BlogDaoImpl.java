package nl.christine.websiteserver.dao.impl;

import nl.christine.websiteserver.dao.BlogDao;
import nl.christine.websiteserver.model.BlogEntry;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Profile("!test")
public class BlogDaoImpl implements BlogDao {

    @PersistenceContext(unitName = "defaultPU")
    private EntityManager em;

    private static final String SELECT = "select a from ";

    @Override
    public BlogEntry getBlog(String site, String language) {

        TypedQuery<BlogEntry> query = em.createQuery(
                SELECT + BlogEntry.class.getSimpleName()
                        + " a where a.site = :site and (a.language = :language OR a.language = 'nl') order by a._id desc ",
                BlogEntry.class);

        return query.setParameter("site", site)
                .setParameter("language", language)
                .setMaxResults(1).getSingleResult();
    }

    @Override
    public BlogEntry getBlog(String site, String language, String id) {

        TypedQuery<BlogEntry> query = em.createQuery(
                SELECT + BlogEntry.class.getSimpleName()
                        + " a  where a.site = :site and (a.language = :language OR a.language = 'nl') and a.id = :id ",
                BlogEntry.class);

        return query.setParameter("site", site)
                .setParameter("language", language)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void insert(BlogEntry blogEntry) {
        em.persist(blogEntry);
    }

    @Override
    public BlogEntry getPrevious(String site, String language, long id) {

        BlogEntry entry = getBlog(id);

        TypedQuery<BlogEntry> query = em.createQuery(
                SELECT + BlogEntry.class.getSimpleName()
                        + " a  where a.site = :site and a.language = :language and a.dateMillis < :date order by a.dateMillis desc",
                BlogEntry.class);

        try {
            return query.setParameter("site", site)
                    .setParameter("language", language)
                    .setParameter("date", entry.getDateMillis())
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return entry;
        }
    }

    @Override
    public BlogEntry getBlog(long id) {
        return em.find(BlogEntry.class, id);
    }

    @Override
    public BlogEntry getNext(String site, String language, long id) {

        BlogEntry entry = getBlog(id);

        TypedQuery<BlogEntry> query = em.createQuery(
                SELECT + BlogEntry.class.getSimpleName()
                        + " a  where a.site = :site and a.language = :language and a.dateMillis > :date order by a.dateMillis asc",
                BlogEntry.class);

        try {
            return query.setParameter("site", site)
                    .setParameter("language", language)
                    .setParameter("date", entry.getDateMillis())
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return entry;
        }
    }

    @Override
    public List<BlogEntry> getBlogs(String site, String language, int count) {
        TypedQuery<BlogEntry> query = em.createQuery(
                SELECT + BlogEntry.class.getSimpleName()
                        + " a where a.site = :site and (a.language = :language OR a.language = 'nl') order by a.dateMillis desc ",
                BlogEntry.class);

        return query.setParameter("site", site)
                .setParameter("language", language)
                .setMaxResults(count)
                .getResultList();
    }


    @Override
    public List<BlogEntry> getAllBlogs(String site, String language) {
        TypedQuery<BlogEntry> query = em.createQuery(
                SELECT + BlogEntry.class.getSimpleName()
                        + " a where a.site = :site and (a.language = :language OR a.language = 'nl') order by a.dateMillis desc ",
                BlogEntry.class);

        return query.setParameter("site", site)
                .setParameter("language", language)
                .getResultList();
    }

    @Override
    public void clear() {
        em.createQuery("DELETE FROM " + BlogEntry.class.getSimpleName()).executeUpdate();
    }

}
