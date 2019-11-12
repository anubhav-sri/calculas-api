package com.anubhav.calculas.controllers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalculasIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnHttpStatusOkWithResultWhenCalledWithAValidExpression() {
        CalculasResponse response = restTemplate.getForObject("/calculas?query=MTIrMTI=", CalculasResponse.class);
        assertThat(response.isError()).isFalse();
        assertThat(response.getResult()).isEqualTo(24);
    }

    @Test
    public void shouldReturnHttpStatusBadRequestWhenCalledWithInValidExpression() {
        ErrorResponse response = restTemplate.getForObject("/calculas?query=MTIrKysxMysgMTI=", ErrorResponse.class);
        assertThat(response.isError()).isTrue();
        assertThat(response.getMessage()).isEqualTo("Expression passed is invalid");
    }
}
