package com.guy.class23b_and_6;

public class Game {

    public enum GENRE {
        ACTION,
        SPORTS,
        STRATEGY,
        PUZZLE
    }

    private String title;
    private String image;
    private GENRE genre;
    private double rating;
    private long downloads;
    private int year;
    private boolean liked = false;

    public Game() {}

    public String getTitle() {
        return title;
    }

    public Game setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Game setImage(String image) {
        this.image = image;
        return this;
    }

    public GENRE getGenre() {
        return genre;
    }

    public Game setGenre(GENRE genre) {
        this.genre = genre;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public Game setRating(double rating) {
        this.rating = rating;
        return this;
    }

    public long getDownloads() {
        return downloads;
    }

    public Game setDownloads(long downloads) {
        this.downloads = downloads;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Game setYear(int year) {
        this.year = year;
        return this;
    }

    public boolean isLiked() {
        return liked;
    }

    public Game setLiked(boolean liked) {
        this.liked = liked;
        return this;
    }
}
