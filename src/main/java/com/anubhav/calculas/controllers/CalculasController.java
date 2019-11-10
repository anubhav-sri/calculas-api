package com.anubhav.calculas.controllers;


import com.anubhav.calculas.service.ExpressionCalculator;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class CalculasController {
    private ExpressionCalculator calculator;

    @Autowired
    public CalculasController(ExpressionCalculator calculator) {
        this.calculator = calculator;
    }

    @GetMapping("/calculas")
    public CalculasResponse calculate(@RequestParam("query") String expression) throws UnsupportedEncodingException {
        String bytes = new String(Base64.decodeBase64(expression.getBytes()),"utf-8").replace(" ","");
        return new CalculasResponse(false, calculator.calculate(bytes));
    }
}
