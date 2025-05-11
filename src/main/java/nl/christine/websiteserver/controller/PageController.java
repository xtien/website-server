package nl.christine.websiteserver.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import nl.christine.websiteserver.model.Page;
import nl.christine.websiteserver.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name="Page")
public class PageController {

    @Autowired
    private PageService pageService;

    @GetMapping("/site/{site}/{language}/{page}")
    public Page GetPage(@PathVariable String site, @PathVariable String language, @PathVariable String page) {

        try {
            return pageService.getPage(site, language, page);
        } catch (Exception e) {
            return null;
        }
    }
}
