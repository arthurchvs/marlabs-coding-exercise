package com.chaves.marlabscodingexercise.adapter.web;

import com.chaves.marlabscodingexercise.core.QuestionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class BotExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(BotExceptionAdvice.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    String badRequestHandler(BindException ex) {
        logger.error(ex.getMessage());
        return "Invalid Payload";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(QuestionNotFoundException.class)
    String badRequestHandler(QuestionNotFoundException ex) {
        logger.error(ex.getMessage());
        return ex.getMessage();
    }

}
