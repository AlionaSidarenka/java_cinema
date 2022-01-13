package server;

import server.room.Room;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Session {
    static int sessionCount = 0;
    private Integer room;
    private Integer movie;
    private int id;
    private Date startDateTime;

    private Session() {}
    public Session(Integer room, Integer movie, Date startDateTime) {
        sessionCount++;
        id = sessionCount;
        this.room = room;
        this.movie = movie;
        this.startDateTime = startDateTime;
    }

    public Integer getRoom() {
        return room;
    }

    public Integer getMovie() {
        return movie;
    }

    public int getId() {
        return id;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }
}
