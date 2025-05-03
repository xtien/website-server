package nl.christine.websiteserver.controller.admin;

import nl.christine.websiteserver.model.BlogEntry;
import nl.christine.websiteserver.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogEditController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/admin/edit_blog/{site}/{language}")
    public BlogEntry editBlog(@PathVariable String site, @PathVariable String language, @RequestBody BlogEntry request) {

        try {
            BlogEntry entry = blogService.edit(site, language, request);
            return entry;
        } catch (Exception e) {
            return null;
        }
    }
}
