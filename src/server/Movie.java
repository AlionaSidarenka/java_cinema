package server;

import java.util.Date;

public class Movie {
    static int movieCount = 0;
    private String title;
    private int ageRestriction;
    private String[] countries;
    private String director;
    private int id;
    private Date length;
    private float price;

    Movie(String title, int ageRestriction, String[] countries, String director, Date length, float price) {
        movieCount++;
        this.id = movieCount;
        this.title = title;
        this.ageRestriction = ageRestriction;
        this.countries = countries;
        this.director = director;
        this.length = length;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }
}
