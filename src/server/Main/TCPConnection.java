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
//    private ScreenLogger screenLogger = ScreenLogger.getInstance();

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
//            screenLogger.clear();
//            screenLogger.log("server starting....");
//            screenLogger.log("at IP=" + InetAddress.getLocalHost().getHostAddress());
//            screenLogger.log("at port=" + serverSocket.getLocalPort());

            System.out.println("server starting....");
            System.out.println("at IP=" + InetAddress.getLocalHost().getHostAddress());
            System.out.println("at port=" + serverSocket.getLocalPort());

            this.connect();
        } catch (IOException e) {
            System.out.println(e.toString());
            try {
                clientAccepted.close();
                this.connect();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } finally {
            try {/*
                if (sois != null) sois.close();
                if (soos != null) soos.close();*/
                if (clientAccepted != null) clientAccepted.close();
                System.out.println("Closed socket and client");
            } catch (IOException e) {
                System.out.println("Resources are not closed");
            }
        }
    }

    private void connect() {
        while (true) {
            try {
                clientAccepted = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("connection established....");
            try {
                APIService service = new APIService(clientAccepted);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
