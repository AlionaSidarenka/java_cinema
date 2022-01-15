package server.main;

import cinema.connection.Request;
import cinema.connection.Response;
import cinema.model.Session;
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

    public APIService(Socket clientAccepted) throws IOException {
        this.clientAccepted = clientAccepted;
        objectOutputStream = new ObjectOutputStream(clientAccepted.getOutputStream());
        objectOutputStream.flush();

        objectInputStream = new ObjectInputStream(clientAccepted.getInputStream());
        listen();
    }

    public void listen() {
        try {
            Request request = (Request) objectInputStream.readObject();
            // TODO: 1/15/22 error handling by response

            while (request != null) {
                if (request.getUrl().equals("getSessions")) {
                    List<Session> sessions = sessionsOperations.readByDay((String) request.getParams().get("date"));
                    Response<List<Session>> response = new Response("ok", "sessions was send", sessions);
                    objectOutputStream.writeObject(response);
                } else if (request.getUrl().equals("addSession")) {
                    sessionsOperations.create((Session)request.getData());
                    Response response = new Response("Ok", "session was successfully added");
                } else if (request.getUrl().equals("updateSession")) {
                    sessionsOperations.update((Session)request.getData());
                    Response response = new Response("Ok", "session was successfully updated");
                } else if (request.getUrl().equals("deleteSession")) {
                    sessionsOperations.delete((String) request.getParams().get("date"));
                    Response response = new Response("Ok", "session was successfully updated");
                }
                // objectOutputStream.flush();
                request = (Request) objectInputStream.readObject();

            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//    public void loadData(String filepath) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode data = null;
//
//        try {
//            data = objectMapper.readTree(new File(filepath));
//            soos.write(data.get("data").toString());
//            soos.newLine();
//            soos.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//            try {
//                soos.close();
//                sois.close();
//            } catch (IOException err) {
//                err.printStackTrace();
//            }
//        }
//    }
    }
}