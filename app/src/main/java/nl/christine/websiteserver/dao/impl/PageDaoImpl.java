package nl.christine.websiteserver.dao.impl;

import nl.christine.websiteserver.dao.PageDao;
import nl.christine.websiteserver.model.Page;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Component
public class PageDaoImpl implements PageDao {

    @PersistenceContext(unitName = "defaultPU")
    private EntityManager em;

    @Override
    public Page getPage(String site, String page) {

        TypedQuery<Page> query = em.createQuery(
                "select a from " + Page.class.getSimpleName()
                        + " a where " + Page.SITE + " = :site AND " + Page.ID + " = :page",
                Page.class);

        try {
            return query.setParameter("site", site).setParameter("page", page).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
