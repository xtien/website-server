package nl.christine.websiteserver.mock;

import nl.christine.websiteserver.controller.BlogController;
import nl.christine.websiteserver.controller.admin.BlogEditController;
import nl.christine.websiteserver.model.BlogEntry;
import nl.christine.websiteserver.service.BlogService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * User: christine
 * Date: 1/21/19 2:32 PM
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BlogController.class)
@ActiveProfiles("test")
public class WebMockBlogTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlogService service;

    HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
    CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());
    String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";

    private BlogEntry blogEntry = new BlogEntry();

    private String comment = "Blog Tekst";

    @Before
    public void setup() {
        blogEntry.setTitle("Blog title");
        blogEntry.setText("Blog Tekst");
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
    public void getBlogReturnsBlog() throws Exception {

        when(service.getBlog("zaphod", "nl")).thenReturn(blogEntry);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/blog/zaphod/nl")
                        .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                        .param(csrfToken.getParameterName(), csrfToken.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(comment)));

        verify(service).getBlog("zaphod", "nl");
    }
}
