package nl.christine.websiteserver.controller.admin;

import com.rometools.rome.io.FeedException;
import io.swagger.v3.oas.annotations.tags.Tag;
import nl.christine.websiteserver.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin Blog", description = "")
public class BlogInitController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/admin/blogInit")
    public void blogInit(@RequestBody String language) {
        try {
            blogService.initBlog();
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
