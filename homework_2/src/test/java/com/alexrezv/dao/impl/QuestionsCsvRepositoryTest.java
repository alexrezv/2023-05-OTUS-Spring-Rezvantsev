package com.alexrezv.dao.impl;

import com.alexrezv.conf.CsvRepoConfig;
import com.alexrezv.dao.QuestionsRepository;
import com.alexrezv.domain.Answer;
import com.alexrezv.domain.Question;
import io.vavr.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@DisplayName("Репо (дао) для работы с вопросами в CSV файле ")
class QuestionsCsvRepositoryTest {

    private QuestionsRepository repository;

    @Mock
    private CsvRepoConfig csvRepoConfig;

    @BeforeEach
    void setUp() {
        csvRepoConfig = mock(CsvRepoConfig.class);
        when(csvRepoConfig.isSkipFirstRow()).thenReturn(false);
        when(csvRepoConfig.getRowSeparator()).thenReturn(',');
        when(csvRepoConfig.getFileName()).thenReturn("questions_test.csv");

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
