package server.Main;

import server.CRUD.movie.CRUDMovie;
import server.Movie;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.FileAlreadyExistsException;

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

            CRUDMovie crudMovie = new CRUDMovie("");
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
            }
        } catch (IOException | ClassNotFoundException | JAXBException e) {
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
