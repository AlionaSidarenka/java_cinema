package server.CRUD;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

public interface CRUD <T> {
    void create(T t) throws FileAlreadyExistsException;
    T read(String name) throws FileNotFoundException;
    void update(T t) throws FileNotFoundException;
    void delete(String name) throws FileNotFoundException;
}
