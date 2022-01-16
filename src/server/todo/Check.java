package server.todo;

import cinema.model.Seat;
import cinema.model.Session;

public class Check {
    static int checkId = 0;
    Seat[] seats;
    Session session;

    Check(Seat[] seats, Session session) {
        this.seats = seats;
        this.session = session;
        checkId++;
    }
}
