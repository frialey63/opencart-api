package org.pjp.opencart.api.exception;

public class StatusException extends RuntimeException {

    private static final long serialVersionUID = 5126675026636310569L;

    public StatusException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public StatusException(String arg0) {
        super(arg0);
    }

}
