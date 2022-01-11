package server;

import server.User.IUser;

import java.util.Calendar;
import java.util.List;

public interface OperatorTerminal {
     void reservePlace(IUser user, Session session, Seat ... seats);

     void sellPlace(IUser user, Session session, Seat ... seats);

     // List<Session> getSessions(Calendar calendar);
}
