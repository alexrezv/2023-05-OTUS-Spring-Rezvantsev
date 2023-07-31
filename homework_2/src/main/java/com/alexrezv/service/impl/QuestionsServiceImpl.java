package com.alexrezv.service.impl;

import com.alexrezv.dao.QuestionsRepository;
import com.alexrezv.domain.Question;
import com.alexrezv.service.QuestionsService;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionsServiceImpl implements QuestionsService {

    private final QuestionsRepository questionsRepository;

    @Override
    public List<Question> getQuestions() {
        return questionsRepository.getQuestions();
    }

    @Override
    public String stringifyQuestion(Question question) {
        return question.answers()
                .map(answer -> answer.id() + ". " + answer.text())
                .prepend(question.question())
                .mkString("\n");
    }

}
