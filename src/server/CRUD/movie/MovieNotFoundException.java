package server.CRUD.movie;

import java.io.FileNotFoundException;

public class MovieNotFoundException extends FileNotFoundException {
    public MovieNotFoundException(String s) {
        super(s);
    }
}
