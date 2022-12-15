package nl.christine.websiteserver.controller.request;

import java.util.List;

public class CategoriesRequest {

    List<String> categories;

    String language;

    String site;

    public String getSite() {
        return site;
    }

    public String getLanguage() {
        return language;
    }

    public List<String> getCategories() {
        return categories;
    }
}
