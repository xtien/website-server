package nl.christine.websiteserver.controller;

import com.rometools.rome.io.FeedException;
import nl.christine.websiteserver.controller.request.BlogRequest;
import nl.christine.websiteserver.model.BlogEntry;
import nl.christine.websiteserver.service.BlogService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = {"https://zaphod.nl", "https://www.zaphod.nl", "https://pengo.christine.nl", "https://christine.nl", "https://www.christine.nl"}, maxAge = 14400)
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog/{site}/{language}/")
    public BlogEntry getBlog(@PathVariable String site, @PathVariable String language) {

        try {
            return blogService.getBlog(site, language);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/previous_blog/{site}/{language}/")
    public BlogEntry getPrevious(@PathVariable String site, @PathVariable String language, String id) {

        try {
            if (NumberUtils.isCreatable(id)) {
                return blogService.getPrevious(site, language, Long.parseLong(id));
            } else {
                return blogService.getBlog(site, language);
            }
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/next_blog/{site}/{language}/")
    public BlogEntry getNext(@PathVariable String site, @PathVariable String language, String id) {

        try {
            if (NumberUtils.isCreatable(id)) {
                return blogService.getNext(site, language, Long.parseLong(id));
            } else {
                return blogService.getBlog(site, language);
            }
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/blog/init")
    public void init() {
        try {
            blogService.initBlog();
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
