package com.alexrezv.dao;

import com.alexrezv.domain.Question;
import io.vavr.collection.List;

public interface QuestionsRepository {

    List<Question> getQuestions();

}
