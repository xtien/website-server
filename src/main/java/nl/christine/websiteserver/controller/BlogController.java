package nl.christine.websiteserver.controller;

import nl.christine.websiteserver.blog.BlogEntry;
import nl.christine.websiteserver.model.Page;
import nl.christine.websiteserver.service.BlogService;
import nl.christine.websiteserver.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"https://zaphod.nl", "https://www.zaphod.nl", "https://pengo.christine.nl", "https://christine.nl", "https://www.christine.nl"}, maxAge = 14400)
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog/{site}/{language}/{blogId}")
    public BlogEntry GetPage(@PathVariable String site, @PathVariable String language, @PathVariable String blogId) {

        try {
            return blogService.getBlog(site, language, blogId);
        } catch (Exception e) {
            return null;
        }
    }

}
