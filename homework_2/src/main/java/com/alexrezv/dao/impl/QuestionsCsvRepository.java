package com.alexrezv.dao.impl;

import com.alexrezv.conf.CsvRepoConfig;
import com.alexrezv.dao.QuestionsRepository;
import com.alexrezv.domain.Answer;
import com.alexrezv.domain.Question;
import com.alexrezv.dto.QuestionFileRow;
import com.alexrezv.exception.ReadQuestionsException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class QuestionsCsvRepository implements QuestionsRepository {

    private final CsvRepoConfig csvRepoConfig;

    @Override
    public List<Question> getQuestions() {
        CsvMapper mapper = new CsvMapper();

        CsvSchema schema = mapper.schemaFor(QuestionFileRow.class)
                .withColumnSeparator(csvRepoConfig.getRowSeparator())
                .withSkipFirstDataRow(csvRepoConfig.isSkipFirstRow());
        ObjectReader objectReader = mapper.readerFor(QuestionFileRow.class).with(schema);
        try {
            try (InputStream is = getClass().getClassLoader().getResourceAsStream(csvRepoConfig.getFileName())) {
                var data = List.ofAll(objectReader.readValues(is).readAll())
                        .map(it -> (QuestionFileRow) it);
                return buildQuestions(data);
            }
        } catch (IOException e) {
            throw new ReadQuestionsException(e);
        }
    }

    private List<Question> buildQuestions(List<QuestionFileRow> questionFileRows) {
        return questionFileRows
                .groupBy(QuestionFileRow::getQuestionId)
                .mapValues(this::buildQuestion)
                .values().toList();
    }

    private Question buildQuestion(List<QuestionFileRow> questionFileRows) {
        var question = questionFileRows.headOption().map(QuestionFileRow::getQuestion).orNull();
        var answers = questionFileRows.zipWithIndex((qfr, idx) -> new Answer(++idx, qfr.getAnswer(), qfr.isCorrect()));
        return new Question(question, answers);
    }

}
