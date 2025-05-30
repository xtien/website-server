package nl.christine.websiteserver.dao.impl;

import nl.christine.websiteserver.dao.PageDao;
import nl.christine.websiteserver.model.Page;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
@Profile("!test")
public class PageDaoImpl implements PageDao {

    @PersistenceContext(unitName = "defaultPU")
    private EntityManager em;

    @Override
    public Page getPage(String site, String language, String page) {

        TypedQuery<Page> query = em.createQuery(
                "select a from " + Page.class.getSimpleName()
                        + " a where a.site = :site AND a.language = :language AND a.page = :page",
                Page.class);

        try {
            return query.setParameter("site", site).setParameter("language", language).setParameter("page", page).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void persist(Page page) {
        em.persist(page);
    }
}
