package server.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPConnection {
    private Integer port;
    private ServerSocket serverSocket = null;
    private Socket clientAccepted = null;
    private ObjectInputStream sois = null;
    private ObjectOutputStream soos = null;
    public TCPConnection(Integer port) {
        this.port = port;
    }

    public void connect() {
        try {
            serverSocket = new ServerSocket(this.port);
            System.out.println("server starting....");
            System.out.println("at IP=" + InetAddress.getLocalHost().getHostAddress());
            System.out.println("at port=" + serverSocket.getLocalPort());

            clientAccepted = serverSocket.accept();
            System.out.println("connection established....");

            sois = new ObjectInputStream(clientAccepted.getInputStream());
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());
            String clientMessageRecieved = (String) sois.readObject();

            while (!clientMessageRecieved.equals("quite")) {
                System.out.println("message recieved:" + clientMessageRecieved);

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
