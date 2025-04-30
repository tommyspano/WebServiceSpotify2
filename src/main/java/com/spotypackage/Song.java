package com.spotypackage;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Song {
    private String name;
    private List<Artist> artists;
    private Album album;

    public String getName() {
        return name;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public Album getAlbum() {
        return album;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return name + " - " + artists.get(0).getName();
    }
}
