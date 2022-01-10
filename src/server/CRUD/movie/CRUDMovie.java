package server.CRUD.movie;
import server.CRUD.CRUD;
import server.Movie;
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
    public void create(Movie movie) throws MovieExistsException {
        File file = new File(moviesPath + movie.getTitle() + ".xml");
        if(!file.exists()){
//            TODO uncomment when Maven dependency will be added
//            TODO add try/catch if needed
//            TODO add imports
//            JAXBContext jaxbContext = JAXBContext.newInstance(Movie.class);
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//            // output pretty printed
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            jaxbMarshaller.marshal(movie, file);
        } else {
            throw new MovieExistsException("\"" + movie.getTitle() + "\"", null, "Такой фильм уже существует");
        }
    }

    @Override
    public Movie read(String name) throws MovieNotFoundException {
        File file = new File(moviesPath + name + ".xml");
        if(file.exists()){
//            TODO uncomment when Maven dependency will be added
//            TODO add try/catch if needed
//            TODO add imports
//            JAXBContext jaxbContext = JAXBContext.newInstance(Movie.class);
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            Movie movie = (Movie) jaxbUnmarshaller.unmarshal(file);
//            return movie;
        } else {
            throw new MovieNotFoundException("Фильм \"" + name + "\" не найден");
        }
        return null;
    }

    @Override
    public void update(Movie movie) throws MovieNotFoundException {
        File file = new File(moviesPath + movie.getTitle() + ".xml");
        if(file.exists()){
//            TODO uncomment when Maven dependency will be added
//            TODO add try/catch if needed
//            TODO add imports
//            JAXBContext jaxbContext = JAXBContext.newInstance(Movie.class);
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            Movie movie = (Movie) jaxbUnmarshaller.unmarshal(file);
//            return movie;
        } else {
            throw new MovieNotFoundException("Фильм \"" + movie.getTitle() + "\" не найден");
        }
    }

    @Override
    public void delete(String name) throws MovieNotFoundException {
        File file = new File(moviesPath + name + ".xml");
        if(file.exists()){
            file.delete();
        } else {
            throw new MovieNotFoundException("Фильм \"" + name + "\" не найден");
        }
    }
}
