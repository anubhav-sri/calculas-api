package com.anubhav.calculas.tokenization;

import java.util.Arrays;
import java.util.Optional;

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
        public int getPrecedence() {
            return 0;
        }

    }, BRACKET_CLOSE(')') {
        public int getPrecedence() {
            return 0;
        }

    };
    private Character value;

    Operator(Character value) {
        this.value = value;
    }

    public double operate(double rightOperand, double leftOperand) {
        throw new RuntimeException();
    }

    public int getPrecedence() {
        return 0;
    }

    @Override
    public String getValue() {
        return value.toString();
    }

    public static Operator valueOf(Character ch) {
        return Arrays.asList(Operator.values()).stream().filter(v -> v.value.equals(ch)).findFirst().get();
    }
}
