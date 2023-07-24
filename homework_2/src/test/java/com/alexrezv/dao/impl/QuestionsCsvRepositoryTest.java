package com.alexrezv.dao.impl;

import com.alexrezv.Main;
import com.alexrezv.conf.CsvRepoConfig;
import com.alexrezv.dao.QuestionsRepository;
import com.alexrezv.domain.Answer;
import com.alexrezv.domain.Question;
import io.vavr.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Репо (дао) для работы с вопросами в CSV файле ")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Main.class)
class QuestionsCsvRepositoryTest {

    @Autowired
    private CsvRepoConfig csvRepoConfig;

    private QuestionsRepository repository;

    @BeforeEach
    void setUp() {
        repository = new QuestionsCsvRepository(csvRepoConfig);
    }

    @Test
    @DisplayName("должен вернуть все вопросы из указанного файла")
    void shouldReturnAllQuestionsFromFile() {
        List<Question> expected = List.of(
                new Question("question1", List.of(
                        new Answer(1L, "answer1", true),
                        new Answer(2L, "answer2", true),
                        new Answer(3L, "answer3", false)
                )),
                new Question("question2", List.of(
                        new Answer(1L, "answer1", false),
                        new Answer(2L, "answer2", false),
                        new Answer(3L, "answer3", true)
                ))
        );

        List<Question> actual = repository.getQuestions();

        assertThat(actual)
                .isNotEmpty()
                .containsAll(expected);
    }

}
