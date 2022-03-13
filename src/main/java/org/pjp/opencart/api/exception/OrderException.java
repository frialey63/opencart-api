package org.pjp.opencart.api.exception;

public class OrderException extends Exception {

    private static final long serialVersionUID = 2577370079903277458L;

    private final Object error;

    public OrderException(Object error) {
        super();
        this.error = error;
    }

    public Object getError() {
        return error;
    }

}
