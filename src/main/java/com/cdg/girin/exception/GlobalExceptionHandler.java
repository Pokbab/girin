package com.cdg.girin.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BindException.class)
	public ModelAndView handlerWebccException(HttpServletResponse response, BindException exception) throws Exception {
		ModelAndView mav = new ModelAndView("script-alert");
		
		StringBuilder message = new StringBuilder();
		for (ObjectError objectError : exception.getAllErrors()) {
			message.append(objectError.getDefaultMessage());
		}
		
		mav.addObject("message", message.toString());
		return mav;
	}
}
