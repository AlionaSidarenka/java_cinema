package server.crud.session;

import java.io.FileNotFoundException;

public class SessionNotFoundException extends FileNotFoundException {
    public SessionNotFoundException(String s) {
        super(s);
    }
}
