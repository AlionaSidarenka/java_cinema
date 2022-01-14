package server.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import server.Seat;

@JsonDeserialize(as = Room.class)
public class Room {
    @JsonProperty("seats")
    Seat[][] seats;

    @JsonProperty("name")
    private String name;

    public Room(int[] places, String name) {
        seats = new Seat[places.length][];
        for (int i = 0; i < places.length; i++) {
            seats[i] = new Seat[places[i]];
            for (int j = 0; j < places[i]; j++) {
                seats[i][j] = new Seat(i, j);
            }
        }
    }

    public String getName() {
        return this.name;
    }

    private Room(){}
}
