package org.everyagile.everyagile.advice;

public class CNotMemberException extends RuntimeException{
    public CNotMemberException(String msg, Throwable t) {
        super(msg, t);
    }
    public CNotMemberException(String msg) {
        super(msg);
    }

    public CNotMemberException() {
        super();
    }
}
