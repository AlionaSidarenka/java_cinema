package server.Main;

import javax.swing.*;

public class ScreenLogger extends JTextArea {
    private static ScreenLogger singleton = null;
    final static String newline = "\n";

    private ScreenLogger(int rows, int columns) {
        super(rows, columns);
    }

    public static ScreenLogger getInstance() {
        if (singleton == null) {
            throw new AssertionError("You have to call init first");
        }

        return singleton;
    }

    public synchronized static ScreenLogger init(int rows, int columns) {
        if (singleton == null) {
            singleton = new ScreenLogger(rows, columns);
        }
        return singleton;
    }

    void log(String message) {
        this.append(message + newline);
        scrollDown();
    }

    public void scrollDown(){
        this.setCaretPosition(this.getText().length());
    }

    void clear() {
       this.selectAll();
       this.replaceSelection("");
    }
}
