package com.anubhav.calculas;


import com.anubhav.calculas.service.ExpressionCalculator;
import com.anubhav.calculas.tokenization.Tokenizer;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpressionCalculatorTest {

    @Test
    public void shouldCalculateAndReturnTheResultOfExpression() {
        String expr = "2+2-3+(12+54)";
        Double result = new ExpressionCalculator(new Tokenizer()).calculate(expr);
        assertThat(result).isEqualTo(67.00);
    }
}