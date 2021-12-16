package nl.christine.websiteserver.blog;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "blog")
public class BlogEntry {

    public static final String SITE = "site";
    public static final String LANGUAGE = "language";
    private static final String DATE = "date";
    private static final String DATE_STRING = "date_string";
    private static final String TITLE = "title";
    private static final String TEXT = "text";

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

    @Column(name=DATE)
    @JsonProperty(DATE)
    private long dateMillis;

    @Transient
    @JsonProperty(DATE_STRING)
    private long dateString;

    @Column(name = TITLE)
    @JsonProperty(TITLE)
    private String title;

    @Column(name = TEXT)
    @JsonProperty(TEXT)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String text;
}
