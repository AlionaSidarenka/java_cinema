package server.main;

import server.Session;
import server.crud.session.CRUDSession;
import server.crud.session.SessionNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class APIService {
    Socket clientAccepted = null;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    private CRUDSession sessionsOperations = new CRUDSession();

    public APIService(Socket clientAccepted) throws IOException {
        this.clientAccepted = clientAccepted;
        inputStream = clientAccepted.getInputStream();
        outputStream = clientAccepted.getOutputStream();
        listen();
    }

    protected static <T> T unmarshall(String xml, Class<T> clazz)
            throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        T obj = clazz.cast(unmarshaller.unmarshal(new StringReader(xml)));
        return obj;
    }

    public void listen() {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();


            Request request = (Request) unmarshaller.unmarshal(inputStream);

            if (request != null) {
                if (request.getUrl().equals("getSessions")) {
                    //this.loadData("src/data/sessions/1.json");
                    List<Session> sessions = sessionsOperations.readByDay((String) request.getParams().get("date"));
                    Response<List<Session>> response = new Response("Ok", "Works!!!!", sessions);
                    Marshaller marshaller = JAXBContext.newInstance(Response.class).createMarshaller();
                    marshaller.marshal(response, outputStream);
                    clientAccepted.shutdownOutput();

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
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SessionNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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