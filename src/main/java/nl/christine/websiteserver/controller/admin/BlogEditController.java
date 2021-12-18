package nl.christine.websiteserver.controller.admin;

import nl.christine.websiteserver.model.BlogEntry;
import nl.christine.websiteserver.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"https://zaphod.nl", "https://www.zaphod.nl", "https://pengo.christine.nl", "https://christine.nl", "https://www.christine.nl"}, maxAge = 14400)

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
