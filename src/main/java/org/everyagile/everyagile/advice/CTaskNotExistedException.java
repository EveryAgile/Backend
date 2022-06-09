package org.everyagile.everyagile.advice;

public class CTaskNotExistedException extends RuntimeException{
    public CTaskNotExistedException(String msg, Throwable t) {
        super(msg, t);
    }
    public CTaskNotExistedException(String msg) {
        super(msg);
    }

    public CTaskNotExistedException() {
        super();
    }
}
