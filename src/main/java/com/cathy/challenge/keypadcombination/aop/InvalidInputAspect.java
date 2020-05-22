package com.cathy.challenge.keypadcombination.aop;

import com.cathy.challenge.keypadcombination.exceptions.InvalidInputException;
import com.cathy.challenge.keypadcombination.exceptions.InvalidInputSizeException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Aspect
public class InvalidInputAspect {

    @Value("${input.size.min:7}")
    private int minSize;
    @Value("${input.size.max:10}")
    private int maxSize;

    @Before("execution(* * (@com.cathy.challenge.keypadcombination.annotations.Validate (*),..)) && args(input,..))")
    public void validate(String input) {
        if(StringUtils.isEmpty(input) || input.length() < minSize || input.length() > maxSize){
            throw new InvalidInputSizeException();
        }
        try {
            Long.parseLong(input);
        } catch (Exception e) {
            throw new InvalidInputException();
        }
    }
}
