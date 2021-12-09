package nl.christine.websiteserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "page")
public class Page {

    public static final String SITE = "site";
    public static final String ID = "id";
    public static final String LANGUAGE = "language";
    public static final String TITLE = "title";
    public static final String PAGE = "page";
    private static final String TEXT = "text";

    @JsonIgnore
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = SITE)
    @JsonProperty(SITE)
    private String site;

    @Column(name = LANGUAGE)
    @JsonProperty(LANGUAGE)
    private String language;

    @Column(name = PAGE)
    @JsonProperty(PAGE)
    private String page;

    @Column(name = TITLE)
    @JsonProperty(TITLE)
    private String title;

    @Transient
    @JsonProperty(TEXT)
    private String text;

    public Page(){

    }

    public Page(String site, String language, String pageId) {
        this.site = site;
        this.language = language;
        this.page = pageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setText(String text) {
        this.text = text;
    }
}
