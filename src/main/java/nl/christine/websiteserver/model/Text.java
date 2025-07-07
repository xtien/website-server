package nl.christine.websiteserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;

public class Text {

    private static final String LANGUAGE = "language";
    private static final String TEXT = "text";

    @Id
    @Column
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long _id;

    @Column
    @JsonIgnore
    private long blogEntryId;

    @Column(name = LANGUAGE)
    @JsonProperty(LANGUAGE)
    private String language;

    @Column(name = TEXT)
    @JsonProperty(TEXT)
    @Lob
    @JdbcTypeCode(Types.LONGVARCHAR)
    private String text;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
