package com.chaves.marlabscodingexercise.core;

import com.chaves.marlabscodingexercise.adapter.repository.QuestionsCacheImpl;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;


class GetQuestionUseCaseTest {

    @Test
    public void testGetQuestion(){
        QuestionsCache questionsCache = new QuestionsCacheImpl();
        GetQuestionUseCase getQuestionUseCase = new GetQuestionUseCase(questionsCache);
        List<String> actual = Collections.singletonList(getQuestionUseCase.getQuestion());
        List<String> expected = Collections.singletonList("Please sum the numbers( \\d+,)+ \\d+");
        assertLinesMatch(expected, actual);
    }

}