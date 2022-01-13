package server.Main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;

public class APIService {
    private BufferedReader sois = null;
    private BufferedWriter soos = null;

    public APIService(Socket clientAccepted) throws IOException {
        soos = new BufferedWriter(new OutputStreamWriter(clientAccepted.getOutputStream()));
        sois = new BufferedReader(new InputStreamReader(clientAccepted.getInputStream()));

        this.listen();
    }

    public void listen() throws IOException {
        while (true) { // is it fine?
            String request = sois.readLine();

            if (request != null && request.equals("get")) {
                this.loadSessions();
            }
        }
    }

    public void loadSessions() {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode listSessions = null;

        try {
            listSessions = objectMapper.readTree(new File("src/data/sessions/1.json"));
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
