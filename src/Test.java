import cinema.model.Session;
import server.MovieFactory;
import server.crud.session.CRUDSession;
import server.crud.session.SessionExistsException;
import server.crud.session.SessionNotFoundException;

import cinema.util.RoomFactory;
import cinema.util.RoomType;

import javax.xml.bind.JAXBException;

import java.time.LocalDateTime;


public class Test {
    public static void main(String[] args) throws JAXBException, SessionNotFoundException, SessionExistsException {

//        crateSessions();
        createMovies();
//
//        CRUDSession crudSession = new CRUDSession();
//        List<Session> sessions = crudSession.readByDay("2022.10.12");
//        sessions.stream().forEach(session -> {
//            System.out.println(session.getMovie().getTitle());
//        });



//        new Movie("Зеленая миля", 16, "Фрэнк Дарабонт", "03:09", 10.0, "США");

//        CRUDSession crud = new CRUDSession();
//
//        Session ses = crud.read("2022-01-14 22-53");
//        LocalDateTime cal = ses.getStartDateTime();
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm");
//
//
//        System.out.println(ses.getStartDateTime().format(dtf));
    }

    static void createMovies(){
        MovieFactory mf = new MovieFactory();
        mf.init();
    }

    static void crateSessions(){
        CRUDSession crud = new CRUDSession();
        LocalDateTime startDateTime = LocalDateTime.now();
        int hour = 0;
        for (int i = 0; i < 10; i++) {
            crud.create(new Session(RoomFactory.getInstance().getRoom(RoomType.B), MovieFactory.getMovie(), startDateTime.plusHours(hour++)));
        }
    }

}
