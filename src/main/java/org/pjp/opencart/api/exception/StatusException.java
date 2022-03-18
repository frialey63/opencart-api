package org.pjp.opencart.api.exception;

/**
 * Exception which may occur by any operation REST call.
 * @author developer
 *
 */
public class StatusException extends RuntimeException {

    private static final long serialVersionUID = 5126675026636310569L;

    /**
     * @param msg The message
     * @param throwable The throwable
     */
    public StatusException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    /**
     * @param msg The message
     */
    public StatusException(String msg) {
        super(msg);
    }

}
