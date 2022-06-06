package org.everyagile.everyagile.advice;

public class CRegionFailedException extends RuntimeException{
    public CRegionFailedException(String msg, Throwable t) {
        super(msg, t);
    }
    public CRegionFailedException(String msg) {
        super(msg);
    }

    public CRegionFailedException() {
        super();
    }
}
