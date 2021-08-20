package com.michael.assignment.exception;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.webjars.NotFoundException;

/**
 * @author michaelwang on 2021-08-19
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class )
    public String handleInternalServerError(Model model){
        model.addAttribute("message","Internal Server Error, Please click Home to return homepage.");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleError(Model model){
        return "error";
    }

}
