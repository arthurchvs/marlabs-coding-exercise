package com.chaves.marlabscodingexercise.adapter.web;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CheckAnswerRequestDto {

    @NotBlank
    @Pattern(regexp="Please sum the numbers( -?\\d+,)+ -?\\d+")
    private String question;

    @NotNull
    private Integer answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }
}
