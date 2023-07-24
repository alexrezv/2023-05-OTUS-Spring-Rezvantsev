package com.alexrezv.service.impl;

import com.alexrezv.Main;
import com.alexrezv.dao.QuestionsRepository;
import com.alexrezv.domain.Answer;
import com.alexrezv.domain.Question;
import com.alexrezv.service.IOService;
import com.alexrezv.service.QuestionsService;
import io.vavr.collection.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Сервис для работы с вопросами ")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Main.class, TestContextConfig.class})
class QuestionsServiceImplTest {

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private IOService ioService;

    @BeforeEach
    void setUp() {
        Mockito.reset(questionsRepository, ioService);
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
        verifyNoInteractions(ioService);
    }

    @Test
    @DisplayName("должен напечатать вопрос с вариантами ответа")
    void printQuestionWithAnswers() {
        var question = new Question("q1", List.of(
                new Answer(1L, "a1", true),
                new Answer(2L, "a2", true)
        ));

        questionsService.printQuestionWithAnswers(question);

        InOrder order = inOrder(ioService);
        order.verify(ioService).printLine("q1");
        order.verify(ioService).printLine("1. a1");
        order.verify(ioService).printLine("2. a2");
        order.verifyNoMoreInteractions();
        verifyNoInteractions(questionsRepository);
    }

}
