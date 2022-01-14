package server;

import server.user.IUser;

public interface OperatorTerminal {
     void reservePlace(IUser user, Session session, Seat ... seats);

     void sellPlace(IUser user, Session session, Seat ... seats);

     // List<Session> getSessions(Calendar calendar);
}
