package nl.christine.websiteserver.controller.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import nl.christine.websiteserver.controller.result.LogoutResult;
import nl.christine.websiteserver.controller.result.Result;
import nl.christine.websiteserver.model.BlogEntry;
import nl.christine.websiteserver.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin Blog", description = "")
public class BlogEditController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/editBlog/{site}/{language}")
    public BlogEntry editBlog(@PathVariable String site, @PathVariable String language, @RequestBody BlogEntry request) {

        try {
            BlogEntry entry = blogService.edit(site, language, request);
            return entry;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/deleteBlog/{id}")
    public ResponseEntity<Result> deleteBlog(@PathVariable String id) {

        try {
            blogService.deleteBlog(id);
            HttpStatus status = HttpStatus.OK;
            Result result = new Result();
            return new ResponseEntity<>(result, status);
        } catch (Exception e) {
            return null;
        }
    }
}
