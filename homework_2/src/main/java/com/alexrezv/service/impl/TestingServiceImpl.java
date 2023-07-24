package com.alexrezv.service.impl;

import com.alexrezv.domain.Question;
import com.alexrezv.domain.User;
import com.alexrezv.dto.AnsweredQuestion;
import com.alexrezv.service.GreeterService;
import com.alexrezv.service.IOService;
import com.alexrezv.service.QuestionsService;
import com.alexrezv.service.TestingService;
import com.alexrezv.service.UserService;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.alexrezv.service.UserService.ATTR_FIRSTNAME;
import static com.alexrezv.service.UserService.ATTR_LASTNAME;

@RequiredArgsConstructor
@Service
public class TestingServiceImpl implements TestingService {

    private static final String UNKNOWN = "unknown";

    private final GreeterService greeterService;

    private final IOService ioService;

    private final UserService userService;

    private final QuestionsService questionsService;


    @Override
    public void conductTesting() {
        greeterService.greet();

        var user = getUser();

        var questions = questionsService.getQuestions();

        var results = questions.map(this::askQuestion);

        printResults(user, results);
    }

    private User getUser() {
        var creds = greeterService.askUserCredentials(ATTR_FIRSTNAME, ATTR_LASTNAME);
        return userService.build(
                creds.getOrElse(ATTR_FIRSTNAME, UNKNOWN),
                creds.getOrElse(ATTR_LASTNAME, UNKNOWN));
    }

    private AnsweredQuestion askQuestion(Question question) {
        questionsService.printQuestionWithAnswers(question);
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
