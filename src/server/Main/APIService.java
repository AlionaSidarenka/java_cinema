package server.Main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.util.Map;

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
                ObjectMapper objectMapper = new ObjectMapper();
                Request<Object> map = objectMapper.readValue(request, Request.class);

                if (map != null) {
                    if (map.getUrl().equals("getSessions")) {
                        this.loadData("src/data/sessions/1.json");
                    } else if (map.getUrl().equals("getMovies")) {
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
        JsonNode data = null;

        try {
            data = objectMapper.readTree(new File(filepath));
            soos.write(data.get("data").toString());
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
