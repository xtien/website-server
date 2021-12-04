package nl.christine.websiteserver.controller;

import nl.christine.websiteserver.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {

    @Autowired
    private PageService pageService;

    @GetMapping("/menu/{site}/{language}/{page}")
    public String GetPage(@PathVariable String site, @PathVariable String language, @PathVariable String page) {

        try {
            return pageService.getPage(site, language, page);
        } catch (Exception e) {
            return null;
        }
    }

}
