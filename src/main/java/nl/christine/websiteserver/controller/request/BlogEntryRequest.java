package nl.christine.websiteserver.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlogEntryRequest {

    public static final String SITE = "site";
    public static final String LANGUAGE = "language";
    private static final String DATE = "date";
    private static final String DATE_STRING = "date_string";
    private static final String TITLE = "title";
    private static final String TEXT = "text";
    private static final String SUBJECT = "category";
    private static final String ID = "id";

    @JsonProperty(SITE)
    private String site;

    @JsonProperty(ID)
    private String id;

    @JsonProperty(LANGUAGE)
    private String language;

     @JsonProperty(DATE)
    private long dateMillis;

    @JsonProperty(DATE_STRING)
    private String dateString;

    @JsonProperty(TITLE)
    private String title;

    @JsonProperty(TEXT)
    private String text;

    @JsonProperty(SUBJECT)
    private String category;

    public String getSite() {
        return site;
    }

    public String getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public long getDateMillis() {
        return dateMillis;
    }

    public String getDateString() {
        return dateString;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getCategory() {
        return category;
    }
}
