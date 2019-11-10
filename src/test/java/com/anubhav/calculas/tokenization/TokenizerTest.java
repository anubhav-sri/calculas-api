package com.anubhav.calculas.tokenization;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TokenizerTest {

    private final Tokenizer tokenizer;

    public TokenizerTest() {
        tokenizer = new Tokenizer();
    }

    @Test
    public void shouldBeAbleToTokenizeNumbers() {
        List<Token> tokens = tokenizer.tokenize("12");
        assertThat(tokens).contains(new Number(12.0));
    }

    @Test
    public void shouldBeAbleToTokenizeOperators() {
        List<Token> tokens = tokenizer.tokenize("+-*/()");
        assertThat(tokens).contains(Operator.PLUS);
        assertThat(tokens).contains(Operator.MINUS);
        assertThat(tokens).contains(Operator.MULTIPLY);
        assertThat(tokens).contains(Operator.DIVIDE);
        assertThat(tokens).contains(Operator.BRACKET_OPEN);
        assertThat(tokens).contains(Operator.BRACKET_CLOSE);
    }

    @Test
    public void shouldBeAbleToPreserveTheOrderOfTokens() {
        List<Token> tokens = tokenizer.tokenize("12+34-3*1/(2+0)");
        assertThat(tokens).containsExactly(new Number(12.0),
                Operator.PLUS,
                new Number(34.0),
                Operator.MINUS,
                new Number(3.0),
                Operator.MULTIPLY,
                new Number(1.0),
                Operator.DIVIDE,
                Operator.BRACKET_OPEN,
                new Number(2.0),
                Operator.PLUS,
                new Number(0.0),
                Operator.BRACKET_CLOSE);

    }

}