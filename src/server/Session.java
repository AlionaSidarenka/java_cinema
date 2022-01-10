package server;

import server.User.IUser;
import server.room.Room;

import java.math.BigDecimal;
import java.util.Date;

public class Session implements DateTimeHelper {
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





}
