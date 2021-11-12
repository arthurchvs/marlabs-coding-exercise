package com.chaves.marlabscodingexercise.adapter.web;

import com.chaves.marlabscodingexercise.core.CheckAnswerUseCase;
import com.chaves.marlabscodingexercise.core.GetQuestionUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/")
public class BotRestController {

    private final GetQuestionUseCase questionUseCase;
    private final CheckAnswerUseCase checkAnswerUseCase;

    public BotRestController(GetQuestionUseCase questionUseCase, CheckAnswerUseCase checkAnswerUseCase) {
        this.questionUseCase = questionUseCase;
        this.checkAnswerUseCase = checkAnswerUseCase;
    }

    @GetMapping
    public ResponseEntity<String> getQuestion() {
        String question = questionUseCase.getQuestion();
        return ResponseEntity.ok(question);
    }

    @PostMapping
    public ResponseEntity<String> checkAnswer(@Valid @RequestBody CheckAnswerRequestDto checkAnswerRequestDto) {

        boolean isCorrectAnswer = checkAnswerUseCase.checkAnswer(checkAnswerRequestDto.getQuestion(), checkAnswerRequestDto.getAnswer());

        if(isCorrectAnswer) {
            return ResponseEntity.ok("Correct Answer");
        }

        return ResponseEntity
                .badRequest()
                .body("Wrong Answer");
    }
}
