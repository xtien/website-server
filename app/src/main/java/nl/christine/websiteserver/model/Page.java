package nl.christine.websiteserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

public class Page {

    public static final String SITE = "site";
    public static final String ID = "id";

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @Lob
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
