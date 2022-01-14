package server;

import com.fasterxml.jackson.annotation.JsonProperty;
import server.room.Room;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@XmlRootElement
public class Session {
    static int sessionCount = 0;
    private Room room;
    private Movie movie;
    private int id;

    @JsonProperty("startDateTime")
    private LocalDateTime startDateTime;

    private Session() {
        super();
        this.startDateTime = LocalDateTime.now();
    }

    public Session(Room room, Movie movie, LocalDateTime startDateTime) {
        sessionCount++;
        id = sessionCount;
        this.room = room;
        this.movie = movie;
        this.startDateTime = startDateTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getId() {
        return id;
    }
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

/*public BigDecimal getPrice() {
        BigDecimal ratio = getDateRatio(getStartDateTime());
        return movie.getPrice().multiply(ratio);
    }

    BigDecimal getDateRatio(LocalDateTime date) {
        if (date.getDayOfWeek() == DayOfWeek.MONDAY || date.getDayOfWeek()== DayOfWeek.TUESDAY || date.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
            return PriceRatio.LOW.getRatio();
        } else if (date.getDayOfWeek() == DayOfWeek.THURSDAY) {
            return PriceRatio.MEDIUM.getRatio();
        }

        return PriceRatio.HIGH.getRatio();
    }*/
}
