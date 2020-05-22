package com.cathy.challenge.keypadcombination.aop;

import com.cathy.challenge.keypadcombination.controllers.KeypadCombinationsController;
import com.cathy.challenge.keypadcombination.exceptions.InvalidInputException;
import com.cathy.challenge.keypadcombination.exceptions.InvalidInputSizeException;
import com.cathy.challenge.keypadcombination.service.specs.KeyPadCombinationService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InvalidInputAspectTest {

    @InjectMocks
    private KeypadCombinationsController controller;
    @Mock
    private KeyPadCombinationService service;
    private static InvalidInputAspect aspect = new InvalidInputAspect();

    @BeforeClass
    public static void setup(){
        ReflectionTestUtils.setField(aspect,"minSize",7);
        ReflectionTestUtils.setField(aspect,"maxSize",10);
    }

    @Test
    public void shouldAllowToProceed(){
        final AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(controller);
        aspectJProxyFactory.addAspect(aspect);
        final KeypadCombinationsController proxy = aspectJProxyFactory.getProxy();
        proxy.getCombinations("12345678",0,10);
        verify(service, atMostOnce()).generateKeyPadCombinations(anyString(), anyInt(), anyInt());
    }

    @Test(expected = InvalidInputSizeException.class)
    public void shouldThrowInvalidInputSizeException() {
        final AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(controller);
        aspectJProxyFactory.addAspect(aspect);
        final KeypadCombinationsController proxy = aspectJProxyFactory.getProxy();
        proxy.getCombinations("", 0, 10);
    }

    @Test(expected = InvalidInputSizeException.class)
    public void shouldThrowInvalidInputSizeExceptionWhenInputLengthIsLesserThanMin(){
        final AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(controller);
        aspectJProxyFactory.addAspect(aspect);
        final KeypadCombinationsController proxy = aspectJProxyFactory.getProxy();
        proxy.getCombinations("23",0,10);
    }

    @Test(expected = InvalidInputSizeException.class)
    public void shouldThrowInvalidInputSizeExceptionWhenInputLengthIsGreaterThanMax() {
        final AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(controller);
        aspectJProxyFactory.addAspect(aspect);
        final KeypadCombinationsController proxy = aspectJProxyFactory.getProxy();
        proxy.getCombinations("23345566709", 0, 10);
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowInvalidInputException(){
        final AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(controller);
        aspectJProxyFactory.addAspect(aspect);
        final KeypadCombinationsController proxy = aspectJProxyFactory.getProxy();
        proxy.getCombinations("2345aA23D",0,10);
    }


}