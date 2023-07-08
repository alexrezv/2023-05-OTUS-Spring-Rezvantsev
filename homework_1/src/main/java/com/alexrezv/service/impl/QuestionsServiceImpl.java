package com.alexrezv.service.impl;

import com.alexrezv.dao.QuestionsRepository;
import com.alexrezv.domain.AbstractAnswer;
import com.alexrezv.service.IOService;
import com.alexrezv.service.QuestionsService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuestionsServiceImpl implements QuestionsService {

    private final QuestionsRepository questionsRepository;

    private final IOService ioService;

    @Override
    public void printQuestionsWithAnswers() {
        var questions = questionsRepository.getQuestions();
        questions.forEach(q -> {
            ioService.printLine(q.question());
            q.answers()
                    .map(AbstractAnswer::getText)
                    .zipWithIndex(this::makeIndexedString)
                    .forEach(ioService::printLine);
        });
    }

    private String makeIndexedString(String answer, Integer index) {
        return (index + 1) + ". " + answer;
    }

}
