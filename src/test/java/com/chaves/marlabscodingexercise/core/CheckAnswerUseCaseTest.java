package com.chaves.marlabscodingexercise.core;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckAnswerUseCaseTest {

    @Mock
    private QuestionsCache questionsCache;

    @InjectMocks
    private CheckAnswerUseCase checkAnswerUseCase;

    public static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("Please sum the numbers 1, 2, 3", 6, true),
                Arguments.of("Please sum the numbers 4,2, 3", 9, true),
                Arguments.of("Please sum the numbers 1, 9,30", 40, true),
                Arguments.of("Please sum the numbers100,200,300", 600, true),
                Arguments.of("Please sum the numbers 1, 2, -3", 0, true),
                Arguments.of("Please sum the numbers -6,-4", -10, true),
                Arguments.of("Please sum the numbers 2, 3", 0, false),
                Arguments.of("Please sum the numbers 2, 3, 9", 0, false)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    public void testCheckAnswer(String questionInput, Integer answerInput, Boolean expectedResult) {
        when(questionsCache.contains(questionInput)).thenReturn(true);
        assertEquals(expectedResult, checkAnswerUseCase.checkAnswer(questionInput, answerInput));
    }

    @ParameterizedTest
    @MethodSource("arguments")
    public void testCheckAnswerNotInCache(String questionInput, Integer answerInput, Boolean expectedResult) {
        when(questionsCache.contains(questionInput)).thenReturn(false);
        assertThrows(QuestionNotFoundException.class, () -> checkAnswerUseCase.checkAnswer(questionInput, answerInput));
    }

}