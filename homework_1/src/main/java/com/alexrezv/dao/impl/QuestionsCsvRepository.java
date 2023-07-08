package com.alexrezv.dao.impl;

import com.alexrezv.dao.QuestionsRepository;
import com.alexrezv.domain.CorrectAnswer;
import com.alexrezv.domain.IncorrectAnswer;
import com.alexrezv.domain.Question;
import com.alexrezv.dto.QuestionFileRow;
import com.alexrezv.exception.CsvReadException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
public class QuestionsCsvRepository implements QuestionsRepository {

    private final char rowSeparator;

    private final boolean skipFirstRow;

    private final String fileName;

    @Override
    public List<Question> getQuestions() {
        CsvMapper mapper = new CsvMapper();

        CsvSchema schema = mapper.schemaFor(QuestionFileRow.class)
                .withColumnSeparator(rowSeparator)
                .withSkipFirstDataRow(skipFirstRow);
        ObjectReader objectReader = mapper.readerFor(QuestionFileRow.class).with(schema);
        try {
            try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
                return List.ofAll(objectReader.readValues(is).readAll())
                        .map(it -> (QuestionFileRow) it)
                        .map(this::buildQuestion);
            }
        } catch (IOException e) {
            throw new CsvReadException(e);
        }
    }

    private Question buildQuestion(QuestionFileRow questionFileRow) {
        var answers = List.of(
                new CorrectAnswer(questionFileRow.getCorrectAnswer()),
                new IncorrectAnswer(questionFileRow.getAnswer2()),
                new IncorrectAnswer(questionFileRow.getAnswer3()),
                new IncorrectAnswer(questionFileRow.getAnswer4())
        );
        return new Question(questionFileRow.getQuestion(), answers);
    }

}
