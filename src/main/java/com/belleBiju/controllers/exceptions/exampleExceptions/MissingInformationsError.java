package com.belleBiju.controllers.exceptions.exampleExceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MissingInformationsError {

    private Instant timestamp;
    private Integer status;
    private List<String> error = new ArrayList<>();
    private String message;

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
