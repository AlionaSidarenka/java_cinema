package server.Main;
import server.Session;

class Server {
    public static void main(String args[]) {
        Session session;
        TCPConnection tcpConnection = new TCPConnection(2525);
        tcpConnection.connect();
    }
}

