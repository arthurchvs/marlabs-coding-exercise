package com.chaves.marlabscodingexercise.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CheckAnswerUseCase {
    private static final Logger logger = LoggerFactory.getLogger(CheckAnswerUseCase.class);
    private static final String BASE_QUESTION_NO_SPACES = "Pleasesumthenumbers";

    private final QuestionsCache questionsCache;

    public CheckAnswerUseCase(QuestionsCache questionsCache) {
        this.questionsCache = questionsCache;
    }

    public boolean checkAnswer(String question, Integer answer) {
        logger.info("Checking question [{}]...", question);
        if(questionsCache.contains(question)) {
            logger.info("Found question [{}] in cache, checking if answer is {}...", question, answer);
            Integer sumFromQuestion = getSum(question);
            boolean isCorrect = answer.equals(sumFromQuestion);
            logger.info("Answer is {}", isCorrect ? "CORRECT" : "INCORRECT");
            return isCorrect;
        } else {
            logger.info("Question [{}] not found in cache", question);
            throw new QuestionNotFoundException("Question not in cache");
        }
    }

    private Integer getSum(String question) {
        return Arrays.stream(question.replaceAll(" ", "")
                .replace(BASE_QUESTION_NO_SPACES, "")
                .split(","))
                .mapToInt(Integer::valueOf)
                .sum();
    }

}
