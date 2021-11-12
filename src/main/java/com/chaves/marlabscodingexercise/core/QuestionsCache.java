package com.chaves.marlabscodingexercise.core;

import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface QuestionsCache {

    void add(String question);
    boolean contains(String question);
}
