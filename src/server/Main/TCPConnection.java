package server.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPConnection implements Runnable {
    private Integer port;
    private ServerSocket serverSocket = null;
    private Socket clientAccepted = null;
    private ObjectInputStream sois = null;
    private ObjectOutputStream soos = null;
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
            serverSocket = new ServerSocket(this.port);
            screenLogger.clear();
            screenLogger.log("server starting....");
            screenLogger.log("at IP=" + InetAddress.getLocalHost().getHostAddress());
            screenLogger.log("at port=" + serverSocket.getLocalPort());

            clientAccepted = serverSocket.accept();
            screenLogger.log("connection established....");

            sois = new ObjectInputStream(clientAccepted.getInputStream());
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());
            String clientMessageRecieved = (String) sois.readObject();

            while (!clientMessageRecieved.equals("quite")) {
                screenLogger.log("message recieved:" + clientMessageRecieved);

                clientMessageRecieved = clientMessageRecieved.toUpperCase();
                soos.writeObject(clientMessageRecieved);
                clientMessageRecieved = (String) sois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
        } finally {
            try {
                if (sois != null) sois.close();
                if (soos != null) soos.close();
                if (clientAccepted != null) clientAccepted.close();
                if (serverSocket != null) serverSocket.close();
            } catch (IOException e) {
                System.out.println("Ресурсы не закрыты!!!");
            }
        }
    }
}
