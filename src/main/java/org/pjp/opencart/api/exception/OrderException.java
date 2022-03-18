package org.pjp.opencart.api.exception;

/**
 * Exception which may occur by order operations.
 * @author developer
 *
 */
public class OrderException extends Exception {

    private static final long serialVersionUID = 2577370079903277458L;

    private final Object error;

    /**
     * @param error The error, usually a message
     */
    public OrderException(Object error) {
        super();
        this.error = error;
    }

    public Object getError() {
        return error;
    }

}
