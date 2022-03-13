package org.pjp.opencart.api.bean;

public class Result {

    private String success;

    private Object error;

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
