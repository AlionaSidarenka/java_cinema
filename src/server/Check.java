package server;

public class Check {
    static int checkId = 0;
    Seat[] seats;
    Session session;

    Check() {}
    Check(Seat[] seats, Session session) {
        this.seats = seats;
        this.session = session;
    }
}
