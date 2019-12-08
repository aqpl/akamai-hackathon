package com.hackathon.hackathon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.hackathon.hackathon.ErrorCode.InternalServerError;
import static org.springframework.http.HttpStatus.valueOf;

@RestControllerAdvice
public class BaseController {

  private Logger logger = LoggerFactory.getLogger(BaseController.class);


  private static AppException convertToAppEx(Throwable th) {
    if (th instanceof AppException) return (AppException) th;
    return new AppException(InternalServerError);
  }

  @ExceptionHandler(AppException.class)
  public ResponseEntity<Object> appExceptionHandler(HttpServletRequest req, AppException appEx) {
    Map<String, Object> errorMap = new HashMap<>();
    logger.info("App Exception :",appEx);

    return new ResponseEntity<>(errorMap, valueOf(appEx.statusCode()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> exceptionHAndler(HttpServletRequest req, Exception ex) {
    Map<String, Object> errorMap = new HashMap<>();
    logger.info("Exception :",ex);
    return new ResponseEntity<>(errorMap, valueOf(InternalServerError.statusCode()));
  }

}