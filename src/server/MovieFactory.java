package server;

import cinema.model.Movie;
import cinema.model.Session;
import server.crud.CRUD;
import server.crud.movie.CRUDMovie;
import server.crud.session.CRUDSession;
import cinema.util.RoomFactory;
import cinema.util.RoomType;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MovieFactory {
    private static int counter = 0;
    private static List<Movie> movies = new ArrayList<>();

    private static Calendar calendar = Calendar.getInstance();

    public static Movie getMovie() {
        return new Movie("Movie# " + counter++, 12, "John", "02:12", BigDecimal.valueOf(12.10), "Usa", "Germany");
    }

    public void init() {

        movies.add(new Movie("Зеленая миля", 16, "Фрэнк Дарабонт", "03:09", 10.0, "США"));
        movies.add(new Movie("Побег из Шоушенка", 16, "Фрэнк Дарабонт", "02:22", 12.0, "США"));
//        movies.add(new Movie("Властелин колец - Возвращение короля", 12, "Питер Джексон", "03:21", 7.20, "США",
//                "Новая Зеландия"));
        movies.add(new Movie("Форрест Гамп", 12, "Роберт Земекис", "02:22", 12.0, "США"));
        movies.add(new Movie("Список Шиндлера", 16, "Стивен Спилберг", "03:15", 11.40, "США"));
        movies.add(new Movie("Король Лев", 0, "Роджер Аллерс, Роб Минкофф", "01:28", 7.30, "США"));
        movies.add(new Movie("Интерстеллар", 16, "Кристофер Нолан", "02:49", 9.80,
                "Великобритания", "Канада", "США"));

        CRUD<Movie> crudMovie = new CRUDMovie();
        movies.forEach(movie -> {
            try {
                crudMovie.create(movie);
            } catch (FileAlreadyExistsException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        });

        CRUD<Session> crudSession = new CRUDSession();

        LocalDateTime startDateTime = LocalDateTime.now();
        int second = startDateTime.getSecond();
        startDateTime = startDateTime.minusSeconds(second);

        int hour = 1;

        for (Movie movie : movies) {
            try {
                crudSession.create(new Session(RoomFactory.getInstance().getRoom(RoomType.B), movie, startDateTime.plusHours(hour++)));
            } catch (FileAlreadyExistsException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }


    }

}
