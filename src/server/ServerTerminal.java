package server;

import javax.swing.*;
import java.awt.*;

public class ServerTerminal extends JFrame {
    JTextField t;
    JLabel l;
    ScreenLogger screenLogger;
    JButton b;

    ServerTerminal() {
        l = new JLabel("Enter Port");
        t = new JTextField(20);
        this.setSize(600, 400);

        JPanel p = new JPanel();
        screenLogger = ScreenLogger.init(18, 45);
        b = new JButton("submit");

        p.add(l);
        p.add(t);
        p.add(b);
        p.add(new JScrollPane( screenLogger ), BorderLayout.CENTER );
        this.add(p);

        b.addActionListener(e -> {
            String s = e.getActionCommand();
            if (s.equals("submit")) {
                try {
                    TCPConnection tcpConnection = new TCPConnection(Integer.parseInt(t.getText()));
                    t.setEnabled(false);
                    b.setEnabled(false);
                } catch (NumberFormatException numberFormatException) {
                    screenLogger.log("Please enter numbers in format xxxx");
                }
            }
        });
    }
}
