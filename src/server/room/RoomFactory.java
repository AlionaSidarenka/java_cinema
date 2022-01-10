package server.room;

public class RoomFactory {

    private static RoomFactory roomFactory;

    private RoomFactory(){}

    public static RoomFactory getInstance(){
        if(roomFactory == null){
            roomFactory = new RoomFactory();
        }
        return roomFactory;
    }

    public Room getRoom(RoomType roomType){
        switch (roomType){
            case A -> {
                Room room = new Room(new int[]{15,18,18,18,17,17,16,16,15,19});
                return room;
            }
            default -> {
                return null;
            }
        }
    }
}
