package server;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@XmlRootElement
public class Movie implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    static int movieCount = 0;
    private String title;
    private int ageRestriction;
    private String[] countries;
    private String director;
    private int id;
    private Calendar length;
    private BigDecimal price;

    public Movie(String title, int ageRestriction, String director, Calendar length, BigDecimal price, String ... countries) {
        movieCount++;
        this.id = movieCount;
        this.title = title;
        this.ageRestriction = ageRestriction;
        this.countries = countries;
        this.director = director;
        this.length = length;
        this.price = price;
    }

    private Movie() {}

    public Movie(String title, int ageRestriction, String director, Calendar length, double price, String ... countries) {
        movieCount++;
        this.id = movieCount;
        this.title = title;
        this.ageRestriction = ageRestriction;
        this.countries = countries;
        this.director = director;
        this.length = length;
        this.price = BigDecimal.valueOf(price);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String[] getCountries() {
        return countries;
    }

    public void setCountries(String[] countries) {
        this.countries = countries;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getLength() {
        return length;
    }

    public void setLength(Calendar length) {
        this.length = length;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
