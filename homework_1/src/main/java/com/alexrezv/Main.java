package com.alexrezv;

import com.alexrezv.service.QuestionsService;
import com.alexrezv.service.impl.QuestionsServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        QuestionsService qs = (QuestionsServiceImpl) context.getBean("questionsService");

        qs.printQuestionsWithAnswers();
    }
}
