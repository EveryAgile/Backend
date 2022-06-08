package org.everyagile.everyagile.advice;

public class CProjectNotExistedException extends RuntimeException {
    public CProjectNotExistedException(String msg, Throwable t) {
        super(msg, t);
    }
    public CProjectNotExistedException(String msg) {
        super(msg);
    }

    public CProjectNotExistedException() {
        super();
    }
}
