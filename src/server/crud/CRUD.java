package server.crud;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

public interface CRUD <T> {
    void create(T t) throws FileAlreadyExistsException, JAXBException;
    T read(String name) throws FileNotFoundException, JAXBException;
    List<T> readList(String name) throws FileNotFoundException, JAXBException;
    void update(T t) throws FileNotFoundException, JAXBException;
    void delete(T t) throws FileNotFoundException;
}
