package nl.christine.websiteserver.controller.helper;

import nl.christine.websiteserver.controller.request.BlogEntryRequest;
import nl.christine.websiteserver.model.BlogEntry;

public class BlogEntryHelper {

    private BlogEntryHelper(){
    }

    public static BlogEntry toBlogEntry(BlogEntryRequest request){
        BlogEntry entry = new BlogEntry();
        entry.setTitle(request.getTitle());
        entry.setText(request.getText());
        entry.setDate(request.getDateMillis());
        entry.setDateString(request.getDateString());
        entry.setLanguage(request.getLanguage());
        entry.setCategory(request.getCategory());
        entry.setId(request.getId());
        return entry;
    }
}
