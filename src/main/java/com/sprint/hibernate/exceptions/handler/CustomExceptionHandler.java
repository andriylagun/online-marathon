package com.sprint.hibernate.exceptions.handler;

import com.sprint.hibernate.entity.Marathon;
import com.sprint.hibernate.exceptions.MarathonExistException;
import com.sprint.hibernate.exceptions.MarathonNotFoundByIDException;
import com.sprint.hibernate.service.MarathonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    private final MarathonService marathonService;

    @Autowired
    public CustomExceptionHandler(MarathonService marathonService) {
        this.marathonService = marathonService;
    }

    @ExceptionHandler({MarathonExistException.class, MarathonNotFoundByIDException.class})
    public final ModelAndView  handleMarathonExistException(Exception exception) {
        log.error("handleMarathonExistException : " + exception.getMessage());
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
