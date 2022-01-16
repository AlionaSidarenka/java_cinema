package server.todo;

import cinema.model.Seat;
import cinema.model.Session;
import server.user.IUser;

public class OperatorTerminalImpl implements OperatorTerminal {

    @Deprecated
    @Override
    public void reservePlace(IUser user, Session session, Seat... seats) {
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
