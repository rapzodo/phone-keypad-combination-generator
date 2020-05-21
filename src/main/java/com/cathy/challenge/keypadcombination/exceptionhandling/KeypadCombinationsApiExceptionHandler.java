package com.cathy.challenge.keypadcombination.exceptionhandling;

import com.cathy.challenge.keypadcombination.exceptions.InvalidInputException;
import com.cathy.challenge.keypadcombination.exceptions.InvalidInputSizeException;
import com.cathy.challenge.keypadcombination.models.ApiError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class KeypadCombinationsApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Value("${input.size.min:7}")
    private int minSize;
    @Value("${input.size.max:10}")
    private int maxSize;

    @ExceptionHandler(InvalidInputSizeException.class)
    public ResponseEntity<ApiError> handleInvalidInputSizeException(InvalidInputSizeException ex){
        final ApiError apiError = new ApiError("Invalid size for the input, it must be between " + minSize + " and " + maxSize, ex);
        return ResponseEntity.badRequest()
                .body(apiError);
    }
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ApiError> handleInvalidInputSizeException(InvalidInputException ex){
        final ApiError apiError = new ApiError(ex.getMessage(), ex);
        return ResponseEntity.badRequest()
                .body(apiError);
    }
}
