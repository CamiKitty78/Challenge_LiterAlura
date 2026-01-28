package com.challenge.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO {

    private String title;
    private List<AuthorDTO> authors;
    private List<String> languages;
    private Integer download_count;

    public String getTitle() {
        return title;
    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public Integer getDownload_count() {
        return download_count;
    }
}
