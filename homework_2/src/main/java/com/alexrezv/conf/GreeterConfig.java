package com.alexrezv.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
@Data
@Configuration
public class GreeterConfig {

    @Value("${greeter.greeting}")
    private String greeting;

    @Value("${greeter.user-auth-prompt}")
    private String userAuthPrompt;

}
