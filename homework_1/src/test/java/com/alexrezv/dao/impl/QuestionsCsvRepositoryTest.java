package com.alexrezv.dao.impl;

import com.alexrezv.dao.QuestionsRepository;
import com.alexrezv.domain.CorrectAnswer;
import com.alexrezv.domain.IncorrectAnswer;
import com.alexrezv.domain.Question;
import io.vavr.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Репо (дао) для работы с вопросами в CSV файле ")
class QuestionsCsvRepositoryTest {

    private QuestionsRepository repository;

    @BeforeEach
    void setUp() {
        repository = new QuestionsCsvRepository(',', false, "questions_test.csv");
    }

    @Test
    @DisplayName("должен вернуть все вопросы из указанного файла")
    void shouldReturnAllQuestionsFromFile() {
        List<Question> expected = List.of(
                buildQuestion("question1", "correct1", "incorrect11", "incorrect12", "incorrect13"),
                buildQuestion("question2", "correct2", "incorrect21", "incorrect22", "incorrect23")
        );

        List<Question> actual = repository.getQuestions();

        assertThat(actual)
                .isNotEmpty()
                .containsAll(expected);
    }

    private static Question buildQuestion(String question, String correct, String incorrect1, String incorrect2, String incorrect3) {
        var answers = List.of(
                new CorrectAnswer(correct),
                new IncorrectAnswer(incorrect1),
                new IncorrectAnswer(incorrect2),
                new IncorrectAnswer(incorrect3)
        );
        return new Question(question, answers);
    }
}
