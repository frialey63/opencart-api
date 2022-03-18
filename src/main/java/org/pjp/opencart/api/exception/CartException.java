package org.pjp.opencart.api.exception;

/**
 * Exception which may occur by cart operations.
 * @author developer
 *
 */
public class CartException extends Exception {

    private static final long serialVersionUID = 8655342091229323304L;

    private final Object error;

    /**
     * @param error The error, usually a message
     */
    public CartException(Object error) {
        super();
        this.error = error;
    }

    public Object getError() {
        return error;
    }

}
