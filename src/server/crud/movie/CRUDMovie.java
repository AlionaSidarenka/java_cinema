package server.crud.movie;
import cinema.model.Movie;
import server.crud.CRUD;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class CRUDMovie implements CRUD<Movie> {
    private String moviesPath;

    public CRUDMovie(String moviesPath) {
        this.moviesPath = moviesPath;
    }

    public String getMoviesPath() {
        return moviesPath;
    }

    public void setMoviesPath(String moviesPath) {
        this.moviesPath = moviesPath;
    }

    @Override
    public void create(Movie movie) throws MovieExistsException, JAXBException {
        File file = new File(moviesPath + movie.getTitle() + ".xml");
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
    public void update(Movie movie) throws MovieNotFoundException, JAXBException {
        File file = getFileOrThrowNotFound(movie.getTitle());

        JAXBContext jaxbContext = JAXBContext.newInstance(Movie.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        // TODO update
        // Movie movie = (Movie) jaxbUnmarshaller.unmarshal(file);

        // return movie;
    }

    @Override
    public void delete(String name) throws MovieNotFoundException {
        File file = getFileOrThrowNotFound(name);
        file.delete();
    }

    private File getFileOrThrowNotFound(String name) throws MovieNotFoundException {
        File file = new File(moviesPath + name + ".xml");
        if (!file.exists()) {
            throw new MovieNotFoundException("Фильм \"" + name + "\" не найден");
        }
        return file;
    }
}
