package com.anubhav.calculas.service;

import com.anubhav.calculas.controllers.InvalidExpressionException;
import com.anubhav.calculas.tokenization.Number;
import com.anubhav.calculas.tokenization.Operator;
import com.anubhav.calculas.tokenization.Token;
import com.anubhav.calculas.tokenization.Tokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Stack;

@Service
public class ExpressionCalculator {
    private Tokenizer tokenizer;
    private Stack<Double> operandStack = new Stack();
    private Stack<Operator> operatorStack = new Stack();

    @Autowired
    public ExpressionCalculator(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public Double calculate(String expr) {
        try {
            List<Token> tokens = tokenizer.tokenize(expr);
            for (Token token : tokens) {
                if (token instanceof Number) {
                    operandStack.push(((Number) token).getNumericValue());
                } else {
                    Operator op = (Operator) token;
                    if (op == Operator.BRACKET_OPEN) {
                        operatorStack.push(op);
                    } else if (op == Operator.BRACKET_CLOSE) {
                        while (operatorStack.peek() != Operator.BRACKET_OPEN) {
                            Operator operator = operatorStack.pop();
                            double operand1 = operandStack.pop();
                            double operand2 = operandStack.pop();
                            operandStack.push(operator.operate(operand2, operand1));
                        }
                        operatorStack.pop();
                    } else {
                        while (!operatorStack.isEmpty() &&
                                operatorStack.peek().getPrecedence() >= op.getPrecedence()) {
                            Operator operator = operatorStack.pop();
                            Double operand1 = operandStack.pop();
                            Double operand2 = operandStack.pop();
                            operandStack.push(operator.operate(operand2, operand1));
                        }
                        operatorStack.push(op);
                    }
                }


            }
            while (!operatorStack.isEmpty()) {
                Operator operator = operatorStack.pop();
                Double operand1 = operandStack.pop();
                Double operand2 = operandStack.pop();
                operandStack.push(operator.operate(operand2, operand1));
            }

            return operandStack.pop();
        } catch (RuntimeException ex) {
            throw new InvalidExpressionException();
        }
    }
}
