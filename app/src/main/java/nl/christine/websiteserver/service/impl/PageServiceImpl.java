package nl.christine.websiteserver.service.impl;

import nl.christine.websiteserver.dao.PageDao;
import nl.christine.websiteserver.model.Page;
import nl.christine.websiteserver.service.PageService;
import nl.christine.websiteserver.service.TextFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PageServiceImpl implements PageService {

    @Autowired
    private PageDao pageDao;

    @Autowired
    private TextFileService textFileService;

    @Override
    @Transactional
    public Page getPage(String site, String language, String pageId) {
        Page page = pageDao.getPage(site, language, pageId);

        if (page == null) {
            page = new Page(site, language, pageId);
            String title = textFileService.getTitle(site, language, pageId);
            page.setTitle(title);
            pageDao.persist(page);
        }

        String text = textFileService.getText(site, language, pageId);
        page.setText(text);
        return page;
    }
}
