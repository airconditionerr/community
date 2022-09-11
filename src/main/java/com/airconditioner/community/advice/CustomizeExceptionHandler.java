package com.airconditioner.community.advice;

import com.airconditioner.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author AirConditioner
 * @Date 2022/9/11 13:50
 **/
@ControllerAdvice
public class CustomizeExceptionHandler {




    @ExceptionHandler(Exception.class)
    public ModelAndView handle(HttpServletRequest request,
                               Throwable e,
                               Model model) {
        HttpStatus status = getStatus(request);

        if (e instanceof CustomizeException){
            model.addAttribute("message", e.getMessage());
        } else {
            model.addAttribute("message", "服务错误！！！");
        }
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer code = (Integer)
                request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus status = HttpStatus.resolve(code);
        return (status != null) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
