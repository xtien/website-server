package nl.christine.websiteserver.controller;

import nl.christine.websiteserver.model.BlogEntry;
import nl.christine.websiteserver.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"https://zaphod.nl",
        "https://www.zaphod.nl",
        "https://pengo.christine.nl",
        "https://christine.nl",
        "https://www.christine.nl"}, maxAge = 14400)
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog/{site}/{language}")
    public BlogEntry getBlog(@PathVariable String site, @PathVariable String language) {

        try {
            return blogService.getBlog(site, language);
         } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/blog/{site}/{language}/{id}")
    public BlogEntry getBlogForId(@PathVariable String site, @PathVariable String language, @PathVariable String id) {

        try {
            return blogService.getBlog(site, language, id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/previous_blog/{site}/{language}/{id}")
    public BlogEntry getPrevious(@PathVariable String site, @PathVariable String language, @PathVariable String id) {

        try {
            return blogService.getPrevious(site, language, Long.parseLong(id));
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/next_blog/{site}/{language}/{id}")
    public BlogEntry getNext(@PathVariable String site, @PathVariable String language, @PathVariable String id) {

        try {
            return blogService.getNext(site, language, Long.parseLong(id));
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/blogs/{site}/{language}/{count}")
    public List<BlogEntry> getBlogs(@PathVariable String site, @PathVariable String language, @PathVariable int count) {

        try {
            return blogService.getBlogs(site, language, count);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @GetMapping("/all_blogs/{site}/{language}")
    public List<BlogEntry> getAllBlogs(@PathVariable String site, @PathVariable String language) {

        try {
            return blogService.getAllBlogs(site, language);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
