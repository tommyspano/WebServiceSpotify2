package com.spotypackage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {
    private String name;
    private String release_date;

    public String getName() {
        return name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
