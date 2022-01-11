package server;

import server.room.Room;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Session {
    static int sessionCount = 0;
    private Room room;
    private Movie movie;
    private int id;
    private Date startDateTime;

    public Session(Room room, Movie movie, Date startDateTime) {
        sessionCount++;
        id = sessionCount;
        this.room = room;
        this.movie = movie;
        this.startDateTime = startDateTime;
    }

    public Room getRoom() {
        return room;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getId() {
        return id;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public BigDecimal getPrice() {
        BigDecimal ratio = getDateRatio(startDateTime);
        return movie.getPrice().multiply(ratio);
    }

    int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c.get(Calendar.DAY_OF_WEEK);
    }

    BigDecimal getDateRatio(Date date) {
        if (this.getDay(date) == Calendar.MONDAY || this.getDay(date) == Calendar.TUESDAY || this.getDay(date) == Calendar.WEDNESDAY) {
            return PriceRatio.LOW.getRatio();
        } else if (this.getDay(date) == Calendar.THURSDAY) {
            return PriceRatio.MEDIUM.getRatio();
        }

        return PriceRatio.HIGH.getRatio();
    }
}
