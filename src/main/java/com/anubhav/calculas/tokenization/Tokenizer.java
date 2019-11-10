package com.anubhav.calculas.tokenization;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    public List<Token> tokenize(String expression) {
        ArrayList<Token> tokens = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                double operand = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {

                    operand = (operand * 10) + Character.getNumericValue(expression.charAt(i));
                    i++;
                }
                i--;
                tokens.add(new Number(operand));

            } else
                tokens.add(Operator.valueOf(ch));
        }
        return tokens;
    }
}
