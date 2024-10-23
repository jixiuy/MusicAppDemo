package com.example.musicapp;

// Song.java
public class Song {
    private String title;
    private String artist;
    private String imageResource;

    public Song(String title, String artist, String imageResource) {
        this.title = title;
        this.artist = artist;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getImageResource() {
        return imageResource;
    }
}
