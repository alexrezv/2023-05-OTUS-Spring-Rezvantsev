package com.alexrezv.domain;

import io.vavr.collection.List;

public record Question(String question, List<AbstractAnswer> answers) {
}
