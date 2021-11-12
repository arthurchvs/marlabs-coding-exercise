package com.chaves.marlabscodingexercise.adapter.repository;

import com.chaves.marlabscodingexercise.core.QuestionsCache;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public class QuestionsCacheImpl implements QuestionsCache {

    private final HashSet<String> questions = new HashSet<>();

    @Override
    public void add(String question) {
        questions.add(question);
    }

    @Override
    public boolean contains(String question) {
        return questions.contains(question);
    }
}
