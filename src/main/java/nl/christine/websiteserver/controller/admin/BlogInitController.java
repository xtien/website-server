package nl.christine.websiteserver.controller.admin;

import com.rometools.rome.io.FeedException;
import nl.christine.websiteserver.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class BlogInitController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/admin/blog-init")
    public void init(@RequestBody String language) {
        try {
            blogService.initBlog();
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
