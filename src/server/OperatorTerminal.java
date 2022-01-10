package server;

import server.User.IUser;

public interface OperatorTerminal {
    public void reservePlace(IUser user, Session session, Seat ... seats);

    public void sellPlace(IUser user, Session session, Seat ... seats);
}
