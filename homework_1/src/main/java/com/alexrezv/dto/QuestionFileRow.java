package com.alexrezv.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@JsonPropertyOrder({"question", "correct_answer", "answer2", "answer3", "answer4"})
public class QuestionFileRow {

    private String question;

    @JsonProperty("correct_answer")
    private String correctAnswer;

    private String answer2;

    private String answer3;

    private String answer4;

}
