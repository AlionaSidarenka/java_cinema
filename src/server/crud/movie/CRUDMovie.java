package server.crud.movie;

import cinema.model.Movie;
import lombok.NoArgsConstructor;
import server.crud.CRUD;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
public class CRUDMovie implements CRUD<Movie> {
    private static final String MOVIES_PATH = "src" + File.separator + "resources" + File.separator + "movies" + File.separator;

    @Override
    public void create(Movie movie) throws MovieExistsException, JAXBException {
        File file = new File(MOVIES_PATH + movie.getTitle() + ".xml");
        if (file.exists()) {
            throw new MovieExistsException("\"" + movie.getTitle() + "\"", null, "Такой фильм уже существует");
        } else {
            JAXBContext jaxbContext = JAXBContext.newInstance(Movie.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(movie, file);
        }
    }

    @Override
    public Movie read(String name) throws MovieNotFoundException, JAXBException {
        File file = getFileOrThrowNotFound(name);

        JAXBContext jaxbContext = JAXBContext.newInstance(Movie.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Movie movie = (Movie) jaxbUnmarshaller.unmarshal(file);

        return movie;
    }

    @Override
    public List<Movie> readList(String name) throws FileNotFoundException, JAXBException {
        File dir = new File(MOVIES_PATH);
        File[] listFiles = dir.listFiles();
        List<Movie> movies = new ArrayList<>();

        JAXBContext jaxbContext = JAXBContext.newInstance(Movie.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Arrays.stream(listFiles).forEach(file -> {
            try {
                movies.add((Movie) jaxbUnmarshaller.unmarshal(file));
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        });

        return movies;
    }

    @Override
    public void update(Movie movie) throws MovieNotFoundException, JAXBException {
        File file = getFileOrThrowNotFound(movie.getTitle());

        JAXBContext jaxbContext = JAXBContext.newInstance(Movie.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        // Movie movie = (Movie) jaxbUnmarshaller.unmarshal(file);
        // return movie;
    }

    @Override
    public void delete(Movie movie) throws MovieNotFoundException {
        File file = getFileOrThrowNotFound(movie.getTitle());
        file.delete();
    }

    private File getFileOrThrowNotFound(String name) throws MovieNotFoundException {
        File file = new File(MOVIES_PATH + name + ".xml");
        if (!file.exists()) {
            throw new MovieNotFoundException("Фильм \"" + name + "\" не найден");
        }
        return file;
    }
}
