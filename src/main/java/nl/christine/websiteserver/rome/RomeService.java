package nl.christine.websiteserver.rome;

import com.rometools.rome.io.FeedException;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

public interface RomeService {
    @Transactional
    void initBlog() throws IOException, FeedException;
}
