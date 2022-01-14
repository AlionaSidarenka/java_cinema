package server;

public class Seat {
    private int place;
    private int row;
    private boolean sold;
    private boolean reserved;

    public Seat(int place, int row) {
        this.place = place;
        this.row = row;
        sold = false;
        reserved = false;
    }

    private Seat(){}

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
