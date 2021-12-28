package nl.christine.websiteserver.controller.admin;

import com.rometools.rome.io.FeedException;
import nl.christine.websiteserver.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin(origins = {"https://zaphod.nl", "https://www.zaphod.nl", "https://pengo.christine.nl", "https://christine.nl", "https://www.christine.nl"}, maxAge = 14400)
public class BlogInitController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/admin/blog-init")
    public void init(@RequestBody String language) {
        try {
            blogService.initBlog();
        } catch (FeedException | IOException e) {
            e.printStackTrace();
        }
     }
}
