package com.sprint.hibernate.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler({MarathonExistException.class, EmailExistException.class})
    public ModelAndView handleMarathonExistException(MarathonExistException exception){
        ModelAndView model = new ModelAndView("error-page");
        model.addObject("info", exception.getMessage());
        return model;
    }

//    @ExceptionHandler(NoHandlerFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ModelAndView handleNoHandlerFound(NoHandlerFoundException e, WebRequest request) {
//
//        ModelAndView model = new ModelAndView("error-page");
//        model.addObject("info", e.getMessage());
//        return model;
//    }
}
