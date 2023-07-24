package com.alexrezv.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@JsonPropertyOrder({"question_id", "question", "answer", "is_correct"})
public class QuestionFileRow {

    @JsonProperty("question_id")
    private long questionId;

    private String question;

    private String answer;

    @JsonProperty("is_correct")
    private boolean isCorrect;

}
