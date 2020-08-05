package com.sprint.hibernate.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ApplicationExceptionHandler {

    //Create CustomException (extends RuntimeException) and then other exceptions would extend it

    @ExceptionHandler({MarathonExistException.class})
    public ModelAndView handleMarathonExistException(MarathonExistException exception){
        ModelAndView model = new ModelAndView("error-page");
        model.addObject("info", exception.getMessage());
        return model;
    }

    @ExceptionHandler({EmailExistException.class})
    public ModelAndView handleEmailExistException(EmailExistException exception){
        ModelAndView model = new ModelAndView("error-page");
        model.addObject("info", exception.getMessage());
        return model;
    }

    @ExceptionHandler({SprintExistException.class})
    public ModelAndView handleSprintExistException(SprintExistException exception){
        ModelAndView model = new ModelAndView("error-page");
        model.addObject("info", exception.getMessage());
        return model;
    }

    @ExceptionHandler({TaskExistException.class})
    public ModelAndView handleTaskExistException(TaskExistException exception){
        ModelAndView model = new ModelAndView("error-page");
        model.addObject("info", exception.getMessage());
        return model;
    }
}
