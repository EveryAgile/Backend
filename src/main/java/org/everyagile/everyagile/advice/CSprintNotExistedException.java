package org.everyagile.everyagile.advice;

public class CSprintNotExistedException extends RuntimeException {
    public CSprintNotExistedException(String msg, Throwable t){
        super(msg, t);
    }
    public CSprintNotExistedException(String msg){
        super(msg);
    }

    public CSprintNotExistedException() {
        super();
    }
}
