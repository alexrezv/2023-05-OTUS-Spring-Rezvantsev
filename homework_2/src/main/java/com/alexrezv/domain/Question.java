package com.alexrezv.domain;

import io.vavr.collection.List;
import io.vavr.collection.Set;

public record Question(String question, List<Answer> answers) {

    public Set<Long> getCorrectAnswerIds() {
        return answers
                .filter(Answer::isCorrect)
                .map(Answer::id)
                .toSet();
    }

}
