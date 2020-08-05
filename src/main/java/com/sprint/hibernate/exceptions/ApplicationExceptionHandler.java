package com.sprint.hibernate.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    //Create CustomException (extends RuntimeException) and then other exceptions would extend it

    @ExceptionHandler({MarathonExistException.class})
    public ModelAndView handleMarathonExistException(MarathonExistException exception){
        log.error("MarathonExistException : " + exception.getMessage());
        ModelAndView model = new ModelAndView("error-page");
        model.addObject("info", exception.getMessage());
        return model;
    }

    @ExceptionHandler({EmailExistException.class})
    public ModelAndView handleEmailExistException(EmailExistException exception){
        log.error("EmailExistException : " + exception.getMessage());
        ModelAndView model = new ModelAndView("error-page");
        model.addObject("info", exception.getMessage());
        return model;
    }

    @ExceptionHandler(MarathonNotFoundByIDException.class)
    public final ModelAndView  handleMarathonExistException(Exception exception) {
        log.error("MarathonNotFoundByIDException : " + exception.getMessage());
        ModelAndView modelAndView = new ModelAndView("marathon_error", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("info", exception.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public final ModelAndView handle500Exception(Exception exception) {
        log.error(String.format("Internal Server Error %s ", exception.getMessage()));
        ModelAndView modelAndView = new ModelAndView("marathon_error", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("info", "Some problem, but we are working");
        return modelAndView;
    }

}
