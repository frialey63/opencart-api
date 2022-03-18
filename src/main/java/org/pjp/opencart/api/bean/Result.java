package org.pjp.opencart.api.bean;

public class Result {

    public static Result create(String success, Object error) {
        Result result = new Result();
        result.setSuccess(success);
        result.setError(error);
        return result;
    }

    String success;

    Object error;

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
