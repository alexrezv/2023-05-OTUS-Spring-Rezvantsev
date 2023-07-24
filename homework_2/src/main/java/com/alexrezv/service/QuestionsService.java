package com.alexrezv.service;

import com.alexrezv.domain.Question;
import io.vavr.collection.List;

public interface QuestionsService {

    List<Question> getQuestions();

    void printQuestionWithAnswers(Question question);

}
