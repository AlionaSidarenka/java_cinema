package server;


class Server {
    public static void main(String args[]) {

//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new ServerTerminal();
//            frame.setTitle("Server");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setVisible(true);
//        });

        TCPConnection connection = new TCPConnection(2525);
    }
}
