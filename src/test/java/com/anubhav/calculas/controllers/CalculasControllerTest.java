package com.anubhav.calculas.controllers;

import com.anubhav.calculas.service.ExpressionCalculator;
import com.anubhav.calculas.tokenization.Tokenizer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculasControllerTest {

    @Rule
    public ExpectedException exceptionThrown = ExpectedException.none();

    @Test
    public void shouldReturnTheTotalWhenExpressionIsValid() throws UnsupportedEncodingException {
        String expression = "MTIrMTMrMTI="; //12+13+12
        CalculasResponse response = new CalculasController(new ExpressionCalculator(new Tokenizer())).calculate(expression);
        assertThat(response.isError()).isFalse();
        assertThat(response.getResult()).isEqualTo(37d);
    }

    @Test
    public void shouldReturnTheErrorWhenExpressionIsInValid() throws UnsupportedEncodingException {
        String expression = "MTIrKzEzKzEy";//12++13+12
        exceptionThrown.expect(InvalidExpressionException.class);
        new CalculasController(new ExpressionCalculator(new Tokenizer())).calculate(expression);
    }

}