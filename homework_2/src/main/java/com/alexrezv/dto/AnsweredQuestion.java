package com.alexrezv.dto;

import com.alexrezv.domain.Question;
import io.vavr.collection.Set;

public record AnsweredQuestion(Question question, Set<Long> givenAnswers) {

    public boolean isAnsweredCorrectly() {
        var correctAnswers = question.getCorrectAnswerIds();
        return correctAnswers.intersect(givenAnswers).equals(correctAnswers)
                && givenAnswers.diff(correctAnswers).isEmpty();
    }

}
