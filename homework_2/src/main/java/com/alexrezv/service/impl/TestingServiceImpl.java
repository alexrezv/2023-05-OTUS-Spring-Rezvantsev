package com.alexrezv.service.impl;

import com.alexrezv.domain.Question;
import com.alexrezv.domain.User;
import com.alexrezv.dto.AnsweredQuestion;
import com.alexrezv.service.GreeterService;
import com.alexrezv.service.IOService;
import com.alexrezv.service.QuestionsService;
import com.alexrezv.service.TestingService;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TestingServiceImpl implements TestingService {

    private final GreeterService greeterService;

    private final IOService ioService;

    private final QuestionsService questionsService;


    @Override
    public void conductTesting() {
        greeterService.greet();

        var user = greeterService.authenticateUser();

        var questions = questionsService.getQuestions();

        var results = questions.map(this::askQuestion);

        printResults(user, results);
    }

    private AnsweredQuestion askQuestion(Question question) {
        var q = questionsService.stringifyQuestion(question);
        ioService.printLine(q);
        ioService.printLine("Your answer (number or comma-separated list):");
        var givenAnswers = List.of(ioService.readLine().split(","))
                .map(String::trim)
                .map(Long::valueOf)
                .toSet();
        return new AnsweredQuestion(question, givenAnswers);
    }

    private void printResults(User user, List<AnsweredQuestion> results) {
        ioService.printLine("=======================================");
        ioService.printLine("User: " + user.firstName() + " " + user.lastName());
        ioService.printLine("Test results:");
        results.forEach(q -> {
            ioService.printLine(q.question().question());
            ioService.printLine("Your answer was " + (q.isAnsweredCorrectly() ? "correct" : "incorrect"));
        });
    }

}
