package org.pjp.opencart.api.bean;

/**
 * Bean to represent a result.
 * @author developer
 *
 */
public class Result {

    /**
     * @param success Success message
     * @param error Error message
     * @return The Result
     */
    public static Result create(String success, Object error) {
        Result result = new Result();
        result.setSuccess(success);
        result.setError(error);
        return result;
    }

    // CHECKSTYLE:OFF encapsulation

    String success;

    Object error;

    // CHECKSTYLE:ON

    /**
     * Default constructor required by Jackson.
     */
    public Result() {
        super();
    }

    public boolean ok() {
        return (success != null) || (error == null);
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Result [success=" + success + ", error=" + error + "]";
    }

}
