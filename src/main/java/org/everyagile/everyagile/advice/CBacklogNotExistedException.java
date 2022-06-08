package org.everyagile.everyagile.advice;

public class CBacklogNotExistedException extends RuntimeException{
    public CBacklogNotExistedException(String msg, Throwable t) {
        super(msg, t);
    }
    public CBacklogNotExistedException(String msg) {
        super(msg);
    }

    public CBacklogNotExistedException() {
        super();
    }
}
