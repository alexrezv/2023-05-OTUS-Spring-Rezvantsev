package com.alexrezv.service.impl;

import com.alexrezv.dao.QuestionsRepository;
import com.alexrezv.domain.Answer;
import com.alexrezv.domain.Question;
import com.alexrezv.service.QuestionsService;
import io.vavr.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Сервис для работы с вопросами ")
class QuestionsServiceImplTest {

    private QuestionsService questionsService;

    @Mock
    private QuestionsRepository questionsRepository;

    @BeforeEach
    void setUp() {
        questionsRepository = mock(QuestionsRepository.class);
        questionsService = new QuestionsServiceImpl(questionsRepository);
    }

    @Test
    @DisplayName("должен вернуть вопросы из репо")
    void getQuestions() {
        var question = new Question("q1", List.of(new Answer(1L, "a1", true)));

        when(questionsService.getQuestions()).thenReturn(List.of(question));

        var actual = questionsService.getQuestions();

        assertThat(actual)
                .hasSize(1)
                .contains(question);

        verify(questionsRepository, times(1)).getQuestions();
        verifyNoMoreInteractions(questionsRepository);
    }

    @Test
    @DisplayName("должен превратить вопрос с вариантами ответа в строку")
    void stringifyQuestion() {
        var question = new Question("q1", List.of(
                new Answer(1L, "a1", true),
                new Answer(2L, "a2", true)
        ));

        var expected = questionsService.stringifyQuestion(question);

        assertThat(expected).isEqualTo("q1\n1. a1\n2. a2");

        verifyNoInteractions(questionsRepository);
    }

}
