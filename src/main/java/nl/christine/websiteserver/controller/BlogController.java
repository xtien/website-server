package nl.christine.websiteserver.controller;

import com.rometools.rome.io.FeedException;
import nl.christine.websiteserver.model.BlogEntry;
import nl.christine.websiteserver.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = {"https://zaphod.nl", "https://www.zaphod.nl", "https://pengo.christine.nl", "https://christine.nl", "https://www.christine.nl"}, maxAge = 14400)
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog/{site}/{language}/{blogId}")
    public BlogEntry getPage(@PathVariable String site, @PathVariable String language, @PathVariable String blogId) {

        try {
            return blogService.getBlog(site, language, blogId);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/blog/init")
    public void init(){
        try {
            blogService.initBlog();
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
