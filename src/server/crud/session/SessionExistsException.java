package server.crud.session;

import java.nio.file.FileAlreadyExistsException;

public class SessionExistsException extends FileAlreadyExistsException {

    /**
     * Constructs an instance of this class.
     *
     * @param   file
     *          a string identifying the file or {@code null} if not known
     */
    public SessionExistsException(String file) {
        super(file);
    }

    /**
     * Constructs an instance of this class.
     *
     * @param   file
     *          a string identifying the file or {@code null} if not known
     * @param   other
     *          a string identifying the other file or {@code null} if not known
     * @param   reason
     *          a reason message with additional information or {@code null}
     */
    public SessionExistsException(String file, String other, String reason) {
        super(file, other, reason);
    }
}
