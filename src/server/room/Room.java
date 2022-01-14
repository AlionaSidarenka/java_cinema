package server.room;

import server.Seat;


public class Room {

    Seat[][] seats;


    private String name;

    public Room(int[] places, String name) {
        seats = new Seat[places.length][];
        for (int i = 0; i < places.length; i++) {
            seats[i] = new Seat[places[i]];
            for (int j = 0; j < places[i]; j++) {
                seats[i][j] = new Seat(i, j);
            }
        }
        this.name = name;
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private Room(){}
}
