package com.alexrezv.service.impl;

import com.alexrezv.dao.QuestionsRepository;
import com.alexrezv.service.IOService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

// Класс для замены некоторых бинов в контексте на моки
@Configuration
public class TestContextConfig {

    @Bean
    @Primary
    QuestionsRepository questionsRepository() {
        return Mockito.mock(QuestionsRepository.class);
    }

    @Bean
    @Primary
    IOService ioService() {
        return Mockito.mock(IOService.class);
    }

}
