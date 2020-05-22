package com.cathy.challenge.keypadcombination;


import com.google.common.collect.ImmutableMap;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.RestAssured.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class KeyPadControllerRestAssuredTest {

    @Value("${server.port}")
    private int port;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup(){
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    public void shouldReturnCombinations(){
        given().port(port)
                .when()
                .log().everything()
                .queryParams(ImmutableMap.of("pageOffSet","0","pageSize","10"))
                .get("keypad/v1/generator/phone/23456789")
                .then()
                .log().everything()
                .statusCode(HttpStatus.OK.value());
    }

}
