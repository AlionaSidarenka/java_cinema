package server.Main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPConnection implements Runnable {
    private Integer port;
    private ServerSocket serverSocket = null;
    private Socket clientAccepted = null;
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

            while (true) {
                clientAccepted = serverSocket.accept();
                screenLogger.log("connection established....");
                APIService service = new APIService(clientAccepted);
            }
        } catch (IOException e) {
            screenLogger.log(e.toString());
            try {
                clientAccepted.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } finally {
            try {/*
                if (sois != null) sois.close();
                if (soos != null) soos.close();*/
                if (clientAccepted != null) clientAccepted.close();
                if (serverSocket != null) serverSocket.close();
                screenLogger.log("Closed socket and client");
            } catch (IOException e) {
                screenLogger.log("Resources are not closed");
            }
        }
    }
}
