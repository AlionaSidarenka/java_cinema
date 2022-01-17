package server.crud;

import cinema.connection.Response;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

public interface CRUD <T> {
    Response create(T t) throws FileAlreadyExistsException, JAXBException;
    T read(String name) throws FileNotFoundException, JAXBException;
    List<T> readList(String name) throws FileNotFoundException, JAXBException;
    Response update(T t) throws FileNotFoundException, JAXBException;
    void delete(T t) throws FileNotFoundException;
}
