package server;

import cinema.model.Seat;
import cinema.model.Session;
import server.user.IUser;

public interface OperatorTerminal {
     void reservePlace(IUser user, Session session, Seat... seats);

     void sellPlace(IUser user, Session session, Seat ... seats);

     // List<Session> getSessions(Calendar calendar);
}
