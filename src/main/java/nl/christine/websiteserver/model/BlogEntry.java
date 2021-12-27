package nl.christine.websiteserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import org.apache.commons.lang3.time.FastDateFormat;
import org.hibernate.annotations.Type;
import org.jdom2.Element;

import javax.persistence.*;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "blog_entry")
public class BlogEntry {

    @Transient
    String dateFormat = "yyyy-MM-dd hh:mm:ss";

    public static final String SITE = "site";
    public static final String LANGUAGE = "language";
    private static final String DATE = "date";
    private static final String DATE_STRING = "date_string";
    private static final String TITLE = "title";
    private static final String TEXT = "text";
    private static final String SUBJECT = "category";
    private static final String ID = "id";

    @Transient
    private final String defaultLanguage = "nl";
    @Transient
    private final String defaultSite = "christine";

    @Id
    @Column
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long _id;

    @Column(name = SITE)
    @JsonProperty(SITE)
    private String site;

    @Column(name = ID)
    @JsonProperty(ID)
    private String id;

    @Column(name = LANGUAGE)
    @JsonProperty(LANGUAGE)
    private String language;

    @Column(name = DATE)
    @JsonProperty(DATE)
    private long dateMillis;

    @Column(name = DATE_STRING)
    @JsonProperty(DATE_STRING)
    private String dateString;

    @Column(name = TITLE)
    @JsonProperty(TITLE)
    private String title;

    @Column(name = TEXT)
    @JsonProperty(TEXT)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String text;

    @Column(name = SUBJECT)
    @JsonProperty(SUBJECT)
    private String category;

    public BlogEntry() {

    }

    public BlogEntry(SyndEntry entry) {
        this.language = defaultLanguage;
        this.title = entry.getTitle();
        this.site = defaultSite;
        this.text = "";
        for (SyndContent s : entry.getContents()) {
            if (s.getType().equals("html")) {
                if(s.getValue().contains("wp-content/uploads")){
                    String b = "found";
                }
                text += s.getValue()
                        .replaceAll("christinenl.blog/wp-content/uploads/[0-9]{4,4}/[0-9]{2,2}", "content.christine.nl/website-images")
                        .replace("christinenl.blog", "christine.nl")
                        .replace("christinenl.files.wordpress.com", "content.christine.nl")
                        .replaceAll("content.christine.nl/[0-9]{4,4}/[0-9]{2,2}", "content.christine.nl/website-images")
                 ;
                String c = "found";
            }
        }

        List<Element> elements = entry.getForeignMarkup().stream().filter(d -> d.getName().equals("post_date")).collect(Collectors.toList());
        if (elements.size() > 0) {
            dateString = elements.get(0).getValue();
            try {
                dateMillis = FastDateFormat.getInstance(dateFormat).parse(dateString).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        List<SyndCategory> l = entry.getCategories().stream().filter(c -> c.getTaxonomyUri().equals("category")).collect(Collectors.toList());
        if (l.size() > 0) {
            category = l.get(0).getName();
        }
    }

    public String getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }

    public String getSite() {
        return site;
    }

    public long getDateMillis() {
        return dateMillis;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long get_id() {
        return _id;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(long dateMillis) {
        this.dateMillis = dateMillis;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getDateString() {
        return dateString;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
