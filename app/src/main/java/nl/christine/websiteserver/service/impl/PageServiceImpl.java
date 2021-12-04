package nl.christine.websiteserver.service.impl;

import nl.christine.websiteserver.dao.PageDao;
import nl.christine.websiteserver.model.Page;
import nl.christine.websiteserver.service.PageService;
import nl.christine.websiteserver.service.TextFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageServiceImpl implements PageService {

    @Autowired
    private TextFileService textFileService;

    @Override
    public String getPage(String site, String language, String page) {
        return textFileService.getText(site, language, page);
    }
}
