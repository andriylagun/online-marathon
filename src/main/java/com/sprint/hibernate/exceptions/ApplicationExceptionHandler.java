package com.sprint.hibernate.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

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
        log.error("MarathonNotFoundByIDException     : " + exception.getMessage());
        ModelAndView modelAndView = new ModelAndView("marathon_error", HttpStatus.BAD_REQUEST);
        modelAndView.addObject("info", exception.getMessage());
        return modelAndView;
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
