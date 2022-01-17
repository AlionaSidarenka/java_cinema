package server;

import cinema.connection.Request;
import cinema.connection.Response;
import cinema.model.Movie;
import cinema.model.Session;
import server.crud.movie.CRUDMovie;
import server.crud.session.CRUDSession;


import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class APIService {
    Socket clientAccepted = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    private CRUDSession sessionsOperations = new CRUDSession();
    private CRUDMovie movieOperations = new CRUDMovie();

    public APIService(Socket clientAccepted) throws IOException {
        this.clientAccepted = clientAccepted;
        objectOutputStream = new ObjectOutputStream(clientAccepted.getOutputStream());
        objectOutputStream.flush();

        objectInputStream = new ObjectInputStream(clientAccepted.getInputStream());
        listen();
    }

    public void listen() {
        Request request = null;

        try {
            request = (Request) objectInputStream.readObject();
            // TODO: 1/15/22 error handling by response

            while (request != null) {
                if (request.getUrl().equals("getSessions")) {
                    List<Session> sessions = sessionsOperations.readByDay((String) request.getParams().get("date"));
                    Response<List<Session>> response = new Response("ok", "sessions was send", sessions);
                    objectOutputStream.writeObject(response);
                } else if (request.getUrl().equals("addSession")) {
                    sessionsOperations.create((Session) request.getData());
                    Response response = new Response("Ok", "session was successfully added");
                    objectOutputStream.writeObject(response);
                } else if (request.getUrl().equals("updateSession")) {
                    Response response;
                    List<Session> sessions = (List<Session>) request.getData();
                    if (sessions.size() == 2) {
                        sessionsOperations.delete(sessions.get(1));
                        response = sessionsOperations.create(sessions.get(0));
                    } else {
                        response = sessionsOperations.update(sessions.get(0));
                    }

                    objectOutputStream.writeObject(response);
                } else if (request.getUrl().equals("deleteSession")) {
                    sessionsOperations.delete((Session) request.getData());
                    Response response = new Response("Ok", "session was successfully updated");
                    objectOutputStream.writeObject(response);
                } else if (request.getUrl().equals("getMovies")) {
                    List<Movie> movies = movieOperations.readList("");
                    Response<List<Session>> response = new Response("ok", "sessions was send", movies);
                    objectOutputStream.writeObject(response);
                }
                // objectOutputStream.flush();
                request = (Request) objectInputStream.readObject();

            }
        } catch (JAXBException | IOException e) {
//            e.printStackTrace();
            System.out.println("client disconnected");
            Response response = new Response("NOT OK", e.getMessage());
            try {
                objectOutputStream.writeObject(response);
            } catch (IOException err) {
                err.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Response response = new Response("404", "NOT FOUND");
            try {
                objectOutputStream.writeObject(response);
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }

}