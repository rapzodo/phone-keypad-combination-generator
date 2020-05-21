package com.cathy.challenge.keypadcombination.exceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super("The input is not a valid number");
    }
}
