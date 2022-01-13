package server.Main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.CRUD.movie.CRUDMovie;
import server.Movie;
import server.Session;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Map;


public class TCPConnection implements Runnable {
    private Integer port;
    private ServerSocket serverSocket = null;
    private Socket clientAccepted = null;
    private BufferedReader sois = null;
    private BufferedWriter soos = null;
    private Thread t;
    private ScreenLogger screenLogger = ScreenLogger.getInstance();

    public TCPConnection(Integer port) {
        this.port = port;
        t = new Thread(this, "Server thread");
        t.start();
    }

    @Override
    public void run() {
        try {
            // interface and class for requests
            //
            serverSocket = new ServerSocket(this.port);
            screenLogger.clear();
            screenLogger.log("server starting....");
            screenLogger.log("at IP=" + InetAddress.getLocalHost().getHostAddress());
            screenLogger.log("at port=" + serverSocket.getLocalPort());

            ObjectMapper objectMapper = new ObjectMapper();
            // Map<String, List<Session>> listSessions = objectMapper.readValue(new File("src/data/sessions/1.json"), new TypeReference<Map<String, List<Session>>>(){});
            JsonNode listSessions = objectMapper.readTree(new File("src/data/sessions/1.json"));

            while (true) {
                clientAccepted = serverSocket.accept();
                screenLogger.log("connection established....");

                soos = new BufferedWriter(new OutputStreamWriter(clientAccepted.getOutputStream()));
                sois = new BufferedReader(new InputStreamReader(clientAccepted.getInputStream()));

                String request = sois.readLine();
                soos.write(listSessions.get("data").toString());

                soos.newLine();
                soos.flush();
                soos.close();
                sois.close();
            }
            /*CRUDMovie crudMovie = new CRUDMovie("");
            Movie movie = (Movie) sois.readObject();

            while (movie != null) {
                try {
                    crudMovie.create(movie);
                    screenLogger.log("added Movie....");
                    soos.writeObject("added Movie....");
                } catch (FileAlreadyExistsException e) {
                    screenLogger.log(e.toString());
                    soos.writeObject("movie was not added...");
                }

                screenLogger.log("waiting....");
                movie = (Movie) sois.readObject();
            }*/
        } catch (IOException e) {
            screenLogger.log(e.toString());
        } finally {
            try {
                if (sois != null) sois.close();
                if (soos != null) soos.close();
                if (clientAccepted != null) clientAccepted.close();
                if (serverSocket != null) serverSocket.close();
                screenLogger.log("Closed socket and client");
            } catch (IOException e) {
                screenLogger.log("Resources are not closed");
            }
        }
    }
}

// sendRequest
// get