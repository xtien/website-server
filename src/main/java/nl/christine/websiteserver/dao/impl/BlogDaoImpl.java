package nl.christine.websiteserver.dao.impl;

import nl.christine.websiteserver.model.BlogEntry;
import nl.christine.websiteserver.dao.BlogDao;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

@Component
@Profile("!test")
public class BlogDaoImpl implements BlogDao {

    private static final String ANY = "any";
    @PersistenceContext(unitName = "defaultPU")
    private EntityManager em;

    private static final String SELECT = "select a from ";

    private List<String> supported_languages = Arrays.asList(new String[]{"nl", "en"});

    @Override
    public BlogEntry getBlog(String site, String language) {

        TypedQuery<BlogEntry> query = em.createQuery(
                "select a from " + BlogEntry.class.getSimpleName()
                        + " a where a.site = :site and a.language = :language order by a._id desc ",
                BlogEntry.class);

        return query.setParameter("site", site)
                .setParameter("language", language)
                .setMaxResults(1).getSingleResult();
    }

    @Override
    public BlogEntry getBlog(String site, String language, String id) {

        TypedQuery<BlogEntry> query = em.createQuery(
                SELECT + BlogEntry.class.getSimpleName()
                        + " a  where a.site = :site and " + createLangQuery(language) + " and a.id = :id ",
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

        TypedQuery<BlogEntry> query = em.createQuery(
                SELECT + BlogEntry.class.getSimpleName()
                        + " a  where a.site = :site and " + createLangQuery(language) + " and a._id < :id order by a._id desc",
                BlogEntry.class);

        return query.setParameter("site", site)
                .setParameter("language", language)
                .setParameter("id", id)
                .setMaxResults(1)
                .getSingleResult();
    }

    private String createLangQuery(String language) {
        String LANG_QUERY = "a.language = :language ";
        if (ANY.equalsIgnoreCase(language)) {
            LANG_QUERY = "";
            for (String lang : supported_languages) {
                LANG_QUERY += " or a.language = \'" + lang + "\' ";
            }
            LANG_QUERY = LANG_QUERY.substring(4, LANG_QUERY.length());
        }
        return LANG_QUERY;
    }

    @Override
    public List<BlogEntry> getPreviousList(String site, String language, long id) {
        TypedQuery<BlogEntry> query = em.createQuery(
                SELECT + BlogEntry.class.getSimpleName()
                        + " a  where a.site = :site and " + createLangQuery(language) + "e and a._id < :id  order by a._id desc",
                BlogEntry.class);

        return query.setParameter("site", site)
               .setParameter("id", id)
                .setMaxResults(1)
                .getResultList();
    }

    @Override
    public BlogEntry getBlog(long id) {
        return em.find(BlogEntry.class, id);
    }

    @Override
    public BlogEntry getNext(String site, String language, long id) {
        TypedQuery<BlogEntry> query = em.createQuery(
                SELECT + BlogEntry.class.getSimpleName()
                        + " a  where a.site = :site and " + createLangQuery(language) + " and a._id > :id order by a._id asc",
                BlogEntry.class);

        return query.setParameter("site", site)
                .setParameter("language", language)
                .setParameter("id", id)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    public List<BlogEntry> getBlogs(String site, String language, int count) {

        String q = SELECT + BlogEntry.class.getSimpleName()
                + " a where a.site = :site and " + createLangQuery(language) + "  order by a._id desc ";
        TypedQuery<BlogEntry> query = em.createQuery(
                SELECT + BlogEntry.class.getSimpleName()
                        + " a where a.site = :site and " + createLangQuery(language) + "  order by a._id desc ",
                BlogEntry.class);

        List<BlogEntry> result = query.setParameter("site", site)
                .setMaxResults(count)
                .getResultList();
        return result;
    }


    @Override
    public List<BlogEntry> getAllBlogs(String site, String language) {

        TypedQuery<BlogEntry> query = em.createQuery(
                SELECT + BlogEntry.class.getSimpleName()
                        + " a where a.site = :site and " + createLangQuery(language) + " order by a._id desc ",
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
