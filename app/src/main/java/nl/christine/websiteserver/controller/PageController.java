package nl.christine.websiteserver.controller;

import nl.christine.websiteserver.model.Page;
import nl.christine.websiteserver.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {

    @Autowired
    private PageService pageService;

    @GetMapping("/site/{site}/{language}/{page}")
    public Page GetPage(@PathVariable String site, @PathVariable String language, @PathVariable String page) {

        try {
            Page p =  pageService.getPage(site, language, page);
            return p;
        } catch (Exception e) {
            return null;
        }
    }

}

// java.lang.IllegalArgumentException: org.hibernate.hql.internal.ast.QuerySyntaxException: Page is not mapped [select a from Page a where a.site = :site AND a.language = :language AND a.page = :page]
