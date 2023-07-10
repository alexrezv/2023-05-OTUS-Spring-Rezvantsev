package com.alexrezv.domain;

public final class IncorrectAnswer extends AbstractAnswer {

    public IncorrectAnswer(String text) {
        super(text);
    }

    @Override
    public boolean isCorrect() {
        return false;
    }

}
