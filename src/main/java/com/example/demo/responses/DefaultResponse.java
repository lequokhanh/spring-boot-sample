package com.example.demo.responses;

public class DefaultResponse {
    Integer status = 200;

    String message;

    Object data;

    public DefaultResponse() {
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public DefaultResponse status(Integer status) {
        setStatus(status);
        return this;
    }

    public DefaultResponse message(String message) {
        setMessage(message);
        return this;
    }

    public DefaultResponse data(Object data) {
        setData(data);
        return this;
    }

}
