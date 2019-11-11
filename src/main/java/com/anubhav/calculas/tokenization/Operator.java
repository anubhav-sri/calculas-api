package com.anubhav.calculas.tokenization;

import com.anubhav.calculas.controllers.InvalidExpressionException;

import java.util.Arrays;

public enum Operator implements Token {

    PLUS('+') {
        public double operate(double rightOperand, double leftOperand) {
            return rightOperand + leftOperand;
        }

        public int getPrecedence() {
            return 1;
        }

    }, MINUS('-') {
        public double operate(double rightOperand, double leftOperand) {
            return rightOperand - leftOperand;
        }

        public int getPrecedence() {
            return 1;
        }

    }, MULTIPLY('*') {
        public double operate(double rightOperand, double leftOperand) {
            return rightOperand * leftOperand;
        }

        public int getPrecedence() {
            return 2;
        }

    }, DIVIDE('/') {
        public double operate(double rightOperand, double leftOperand) {
            return rightOperand / leftOperand;
        }

        public int getPrecedence() {
            return 2;
        }

    }, BRACKET_OPEN('(') {

    }, BRACKET_CLOSE(')') {

    };
    public static final int DEFAULT_PRECEDENCE = 0;
    private Character value;

    Operator(Character value) {
        this.value = value;
    }

    public double operate(double rightOperand, double leftOperand) {
        throw new RuntimeException("Method NOT Implemented");
    }

    public int getPrecedence() {
        return DEFAULT_PRECEDENCE;
    }

    @Override
    public String getValue() {
        return value.toString();
    }

    public static Operator valueOf(Character ch) {
        return Arrays.stream(Operator.values())
                .filter(v -> v.value.equals(ch))
                .findFirst().orElseThrow(() -> new InvalidExpressionException("Invalid Operator"));

    }
}
