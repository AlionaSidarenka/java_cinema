package server.crud.session;

import cinema.connection.Response;
import cinema.model.Session;
import lombok.NoArgsConstructor;
import server.crud.CRUD;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
public class CRUDSession implements CRUD<Session> {
    private static final String SESSIONS_PATH = "src" + File.separator + "resources" + File.separator + "sessions" + File.separator;
    private static final String FILE_PATTERN = "YYYY-MM-DD HH-mm";

    @Override
    public Response create(Session session) {

        String fileName = getFileName(session);
        File file = new File(SESSIONS_PATH + fileName + ".xml");
        Response response;

        if (file.exists()) {
            response = this.update(session);
        } else {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Session.class);
                Marshaller jaxbMarshaller = null;
                jaxbMarshaller = jaxbContext.createMarshaller();
                // output pretty printed
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                jaxbMarshaller.marshal(session, file);
                response = new Response("Ok", "session was successfully created");
            } catch (JAXBException e) {
                e.printStackTrace();
                response = new Response("NOT OK", "session was not created");
            }
        }

        return response;
    }

    @Override
    // name pattern "2022-12-03 12-20"
    public Session read(String name) throws SessionNotFoundException, JAXBException {
        File file = getFileOrThrowNotFound(name);
        JAXBContext jaxbContext = JAXBContext.newInstance(Session.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Session session = (Session) jaxbUnmarshaller.unmarshal(file);
        return session;
    }

    @Override
    // name pattern "2022-12-03"
    public List<Session> readList(String name) throws FileNotFoundException, JAXBException {
        File dir = new File(SESSIONS_PATH);
        File[] listFiles = dir.listFiles(new MyFileNameFilter(name));
        List<Session> sessions = new ArrayList<>();
        JAXBContext jaxbContext = JAXBContext.newInstance(Session.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Arrays.stream(listFiles).forEach(file -> {
//            sessions.add(new Session(RoomFactory.getInstance().getRoom(RoomType.A), MovieFactory.getMovie(), LocalDateTime.now()));
            try {
                sessions.add((Session) jaxbUnmarshaller.unmarshal(file));

            } catch (JAXBException e) {
                e.printStackTrace();
            }
        });
        return sessions;
    }


    // name pattern "2022-12-03"
    @Deprecated
    public List<Session> readByDay(String name) throws SessionNotFoundException, JAXBException {
        File dir = new File(SESSIONS_PATH);
        File[] listFiles = dir.listFiles(new MyFileNameFilter(name));
        List<Session> sessions = new ArrayList<>();
        JAXBContext jaxbContext = JAXBContext.newInstance(Session.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Arrays.stream(listFiles).forEach(file -> {
//            sessions.add(new Session(RoomFactory.getInstance().getRoom(RoomType.A), MovieFactory.getMovie(), LocalDateTime.now()));
            try {
                sessions.add((Session) jaxbUnmarshaller.unmarshal(file));

            } catch (JAXBException e) {
                e.printStackTrace();
            }
        });
        return sessions;
    }


    @Override
    public Response update(Session session) {
        String fileName = getFileName(session);
        try {
            File file = getFileOrThrowNotFound(fileName);

            JAXBContext jaxbContext = JAXBContext.newInstance(Session.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(session, file);

            return new Response("Ok", "session was successfully updated");
        } catch (SessionNotFoundException | JAXBException e) {
            return new Response("NOT OK", "session was not updated");
        }
    }

    @Override
    public void delete(Session session) throws SessionNotFoundException {
        String fileName = getFileName(session);
        File file = getFileOrThrowNotFound(fileName);
        file.delete();
    }

    private File getFileOrThrowNotFound(String name) throws SessionNotFoundException {
        File file = new File(SESSIONS_PATH + File.separator + name + ".xml");
        if (!file.exists()) {
            throw new SessionNotFoundException("Сессия \"" + name + "\" не существует");
        }
        return file;
    }

    private String getFileName(Session session){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FILE_PATTERN);
        return session.getStartDateTime().format(dtf);
    }

    static class MyFileNameFilter implements FilenameFilter {

        private final String filePattern;

        public MyFileNameFilter(String filePattern) {
            this.filePattern = filePattern.toLowerCase();
        }

        @Override
        public boolean accept(File dir, String name) {
            return name.toLowerCase().startsWith(filePattern);
        }
    }

}

