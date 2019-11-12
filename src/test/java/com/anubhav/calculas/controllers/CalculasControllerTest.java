package com.anubhav.calculas.controllers;

import com.anubhav.calculas.service.ExpressionCalculatorService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculasControllerTest {

    @Rule
    public ExpectedException exceptionThrown = ExpectedException.none();

    @Mock
    private ExpressionCalculatorService calculatorService;
    private CalculasController calculasController;

    @Before
    public void setUp() {
        calculasController = new CalculasController(calculatorService);
    }

    @Test
    public void shouldReturnTheTotalWhenExpressionIsValid() {
        String expression = "MTIrMTMrMTI="; //12+13+12
        when(calculatorService.calculate("12+13+12")).thenReturn(37d);
        CalculasResponse response = calculasController.calculate(expression);
        assertThat(response.isError()).isFalse();
        assertThat(response.getResult()).isEqualTo(37d);
    }

    @Test
    public void shouldReturnTheErrorWhenExpressionIsInValid() {
        String expression = "MTIrKzEzKzEy";//12++13+12
        exceptionThrown.expect(InvalidExpressionException.class);
        when(calculatorService.calculate("12++13+12"))
                .thenThrow(new InvalidExpressionException("Invaild Expression"));
        new CalculasController(calculatorService).calculate(expression);
    }

    @Test
    public void shouldRemoveWhiteSpaces() {
        String expression = "MTIrMTMrIDEy";//12+13+ 12

        new CalculasController(calculatorService).calculate(expression);
        verify(calculatorService).calculate("12+13+12");
    }

}