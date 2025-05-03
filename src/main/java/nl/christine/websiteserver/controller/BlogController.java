package nl.christine.websiteserver.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import nl.christine.websiteserver.controller.request.CategoriesRequest;
import nl.christine.websiteserver.model.BlogEntry;
import nl.christine.websiteserver.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog/{site}/{language}")
    public BlogEntry getBlog(@PathVariable String site, @PathVariable String language) {

        try {
            BlogEntry entry = blogService.getBlog(site, language);
            return entry;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/blog/{site}/{language}/{id}")
    public BlogEntry getBlogForId(@PathVariable String site, @PathVariable String language, @PathVariable String id) {

        try {
            BlogEntry entry = blogService.getBlogForId(site, language, id);
            return entry;
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
            return null;
        }
    }

    @GetMapping("/all_blogs/{site}/{language}")
    public List<BlogEntry> getAllBlogs(@PathVariable String site, @PathVariable String language) {

        try {
            return blogService.getAllBlogs(site, language);
        } catch (Exception e) {
            return null;
        }
    }
    @PostMapping("/categories/")
    public List<BlogEntry> getCategories(@RequestBody CategoriesRequest request) {

        try {
            List result =  blogService.getBlogsForCategories(request);
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
