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

    @Autowired
    public ExpressionCalculator(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public Double calculate(String expr) {
        Stack<Double> operandStack = new Stack<>();
        Stack<Operator> operatorStack = new Stack<>();
        try {
            List<Token> tokens = tokenizer.tokenize(expr);
            for (Token token : tokens) {
                if (token instanceof Number) {
                    operandStack.push(((Number) token).getNumericValue());
                } else {
                    Operator op = (Operator) token;
                    switch (op) {
                        case BRACKET_OPEN:
                            operatorStack.push(op);
                            break;
                        case BRACKET_CLOSE:
                            handleClosedBracket(operatorStack, operandStack);
                            break;
                        default:
                            operateTillPrecedes(op, operatorStack, operandStack);
                            operatorStack.push(op);
                    }
                }


            }
            operateTheRemaining(operatorStack, operandStack);
            return operandStack.pop();
        } catch (RuntimeException ex) {
            throw new InvalidExpressionException("Expression passed is invalid");
        }
    }

    private void operateTillPrecedes(Operator op, Stack<Operator> operatorStack,
                                     Stack<Double> operandStack) {
        while (!operatorStack.isEmpty() &&
                operatorStack.peek().getPrecedence() >= op.getPrecedence()) {
            handleBinaryOperator(operatorStack, operandStack);
        }
    }

    private void handleBinaryOperator(Stack<Operator> operatorStack, Stack<Double> operandStack) {
        Operator operator = operatorStack.pop();
        Double operand1 = operandStack.pop();
        Double operand2 = operandStack.pop();
        operandStack.push(operator.operate(operand2, operand1));
    }

    private void operateTheRemaining(Stack<Operator> operatorStack, Stack<Double> operandStack) {
        while (!operatorStack.isEmpty()) {
            handleBinaryOperator(operatorStack, operandStack);
        }
    }

    private void handleClosedBracket(Stack<Operator> operatorStack, Stack<Double> operandStack) {
        while (operatorStack.peek() != Operator.BRACKET_OPEN) {
            handleBinaryOperator(operatorStack, operandStack);
        }
        operatorStack.pop();
    }
}
