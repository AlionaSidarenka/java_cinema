package server;

import server.User.IUser;

import java.util.Calendar;
import java.util.List;

public class OperatorTerminalImpl implements OperatorTerminal {

    @Deprecated
    @Override
    public void reservePlace(IUser user, Session session, Seat ... seats) {
        //todo threads can be here
    }

    @Override
    public void sellPlace(IUser user, Session session, Seat ... seats) {
        // TODO: тут остановился
    }

/*    @Override
    public List<Session> getSessions(Calendar calendar) {
        // TODO: add
    }*/
}
