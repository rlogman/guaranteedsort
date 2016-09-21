package com.rlogman.guaranteedrate.sorttest.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.rlogman.guaranteedrate.sorttest.ErrorDescriptor;
import com.rlogman.guaranteedrate.sorttest.InternalException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler {

  public static final String INTERNAL_ERROR_ATTRIBUTE = "grInternalError";

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody void handleGeneralException(Exception ex, HttpServletRequest req,
      HttpServletResponse res) throws HttpMessageNotWritableException, IOException {
    handleException(ex, req, res, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody void handleSystemException(RuntimeException ex, HttpServletRequest req,
      HttpServletResponse res) throws HttpMessageNotWritableException, IOException {
    String msg = ex.getMessage();
    handleException(
        new InternalException("There was an error and we are working to resolve it."
            + ((msg != null) ? msg : ""), ex),
        req, res, HttpStatus.BAD_REQUEST);
  }
  
  public static void handleException(Exception ex, HttpServletRequest req,
      HttpServletResponse res, HttpStatus httpStatus) throws HttpMessageNotWritableException, IOException {
    res.setStatus(httpStatus.value());
    RequestAttributes requestAttributes = new ServletRequestAttributes(req);
    ErrorDescriptor errorDescriptor = ErrorDescriptor.builder().message(ex.getMessage())
        .exceptionMessage(ex.getMessage()).httpStatusCode(res.getStatus()).errorProcessor(DefaultExceptionHandler.class.getName()).build();
    requestAttributes.setAttribute(INTERNAL_ERROR_ATTRIBUTE, errorDescriptor,
        RequestAttributes.SCOPE_REQUEST);
    log.error(ex.getMessage(), ex);
    MappingJackson2HttpMessageConverter jacksonMessageConverter =
        new MappingJackson2HttpMessageConverter();
    jacksonMessageConverter.write(errorDescriptor, MediaType.APPLICATION_JSON,
        new ServletServerHttpResponse(res));
    res.flushBuffer();
  }
}
