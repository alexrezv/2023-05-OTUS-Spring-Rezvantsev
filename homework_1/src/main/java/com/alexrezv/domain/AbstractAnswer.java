package com.alexrezv.domain;

import java.util.Objects;

public abstract sealed class AbstractAnswer permits CorrectAnswer, IncorrectAnswer {

    private final String text;

    protected AbstractAnswer(String text) {
        this.text = text;
    }

    public abstract boolean isCorrect();

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractAnswer that)) {
            return false;
        }
        return Objects.equals(getText(), that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText());
    }
}
