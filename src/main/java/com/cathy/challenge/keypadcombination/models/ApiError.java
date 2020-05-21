package com.cathy.challenge.keypadcombination.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiError {
    private final String errorMessage;
    @JsonIgnore
    private final Exception exception;
    private String originalExceptionMessage;

    public String getOriginalExceptionMessage() {
        return exception.getMessage();
    }

    @JsonGetter
    public String getException(){
        return exception.getClass().getName();
    }
}
