package org.pjp.opencart.api.exception;

public class CartException extends Exception {

    private static final long serialVersionUID = 8655342091229323304L;

    private final Object error;

    public CartException(Object error) {
        super();
        this.error = error;
    }

    public Object getError() {
        return error;
    }

}
