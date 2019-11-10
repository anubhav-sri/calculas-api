package com.anubhav.calculas.configuration;

import com.anubhav.calculas.tokenization.Tokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculasConfiguration {

    @Bean
    public Tokenizer tokenizer() {
        return new Tokenizer();
    }
}
