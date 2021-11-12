package com.chaves.marlabscodingexercise.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GetQuestionUseCase {
    private static final Logger logger = LoggerFactory.getLogger(GetQuestionUseCase.class);

    private static final String BASE_QUESTION = "Please sum the numbers ";

    private static final int LOWER_SIZE_BOUND = 2;
    private static final int UPPER_SIZE_BOUND = 4;

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 10;

    private final QuestionsCache questionsCache;

    public GetQuestionUseCase(QuestionsCache questionsCache) {
        this.questionsCache = questionsCache;
    }

    public String getQuestion() {
        Random random = new Random();
        int size = random.nextInt(UPPER_SIZE_BOUND - LOWER_SIZE_BOUND) + LOWER_SIZE_BOUND;

        String question = random.ints(size, MIN_VALUE, MAX_VALUE)
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", BASE_QUESTION, ""));
        questionsCache.add(question);

        logger.info("Generated question [{}]", question);
        return question;
    }

}
