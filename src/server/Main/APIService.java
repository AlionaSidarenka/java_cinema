package server.Main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;

public class APIService {
    Socket clientAccepted = null;
    private BufferedReader sois = null;
    private BufferedWriter soos = null;

    public APIService(Socket clientAccepted) throws IOException {
        this.clientAccepted = clientAccepted;
        soos = new BufferedWriter(new OutputStreamWriter(clientAccepted.getOutputStream()));
        sois = new BufferedReader(new InputStreamReader(clientAccepted.getInputStream()));

        this.listen();
    }

    public void listen() {
        while (this.clientAccepted.isConnected()) { // is it fine?
            String request = null;

            try {
                request = sois.readLine();

                if (request != null) {
                    if (request.equals("getSessions")) {
                        this.loadData("src/data/sessions/1.json");
                    } else if (request.equals("getMovies")) {
                        this.loadData("src/data/movies/1.json");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void loadData(String filepath) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode listSessions = null;

        try {
            listSessions = objectMapper.readTree(new File(filepath));
            soos.write(listSessions.get("data").toString());
            soos.newLine();
            soos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                soos.close();
                sois.close();
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }
}
