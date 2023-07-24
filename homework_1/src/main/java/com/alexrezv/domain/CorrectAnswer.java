package com.alexrezv.domain;

public final class CorrectAnswer extends AbstractAnswer {

    public CorrectAnswer(String text) {
        super(text);
    }

    @Override
    public boolean isCorrect() {
        return true;
    }

}
