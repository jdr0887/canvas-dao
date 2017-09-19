package org.renci.canvas.dao;

public class CANVASDAOException extends Exception {

    private static final long serialVersionUID = 5033504425787801829L;

    public CANVASDAOException() {
        super();
    }

    public CANVASDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CANVASDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public CANVASDAOException(String message) {
        super(message);
    }

    public CANVASDAOException(Throwable cause) {
        super(cause);
    }

}
