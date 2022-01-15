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
        while (this.clientAccepted.isConnected()) {
            try {
                Request request = (Request) objectInputStream.readObject();

                if (request != null) {
                    if (request.getUrl().equals("getSessions")) {
                        //this.loadData("src/data/sessions/1.json");
                        List<Session> sessions = sessionsOperations.readByDay((String) request.getParams().get("date"));
                        Response<List<Session>> response = new Response("Ok", "Works!!!!", sessions);
                        objectOutputStream.writeObject(response);
                        // objectOutputStream.flush();
                        request = (Request) objectInputStream.readObject();
//                    } else if (map.getUrl().equals("getMovies")) {
//
//                        this.loadData("src/data/movies/1.json");
//                    } else if (map.getUrl().equals("updateSession")) {
//                        // JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, Session.class);
//                        // Session result = objectMapper.readValue(objectMapper.writeValueAsString(map.getData()), Session.class);
//                        Session result = objectMapper.readValue(objectMapper.writeValueAsString(map.getData()), Session.class);
//                        objectMapper.writeValue(new File("src/data/sessions/2.json"), result);
//
//                        soos.write(objectMapper.writeValueAsString(new Response("200", "OK")));
//                        soos.newLine();
//                        soos.flush();
                    }
                }
            } catch (JAXBException | IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
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