package server;

import java.util.Date;

public class Session {
    static int sessionCount = 0;
    private Room room;
    private Movie film;
    private int id;
    private Date startDateTime;

    Session() {}
    Session(Room room, Movie film, Date startDateTime) {
        sessionCount++;
        this.id = sessionCount;
        this.room = room;
        this.film = film;
        this.startDateTime = startDateTime;
    }
}
