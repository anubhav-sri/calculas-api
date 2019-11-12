package com.anubhav.calculas.controllers;


import com.anubhav.calculas.service.ExpressionCalculatorService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
public class CalculasController {
    private ExpressionCalculatorService calculator;

    @Autowired
    public CalculasController(ExpressionCalculatorService calculator) {
        this.calculator = calculator;
    }

    @GetMapping("/calculas")
    public CalculasResponse calculate(@RequestParam("query") String expression) {
        String decodedExpression = decodeAndTrim(expression);
        Double result = calculator.calculate(decodedExpression);
        return new CalculasResponse(result);
    }

    private String decodeAndTrim(@RequestParam("query") String expression) {
        return new String(Base64.decodeBase64(expression.getBytes()), StandardCharsets.UTF_8).replace(" ", "");
    }
}
