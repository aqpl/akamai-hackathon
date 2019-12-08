package com.hackathon.hackathon;

import java.util.Map;

public class AppException extends RuntimeException {

  private ErrorCode error;
  private Map<String, Object> params;

  public AppException(ErrorCode error) {
    this.error = error;
  }

  public ErrorCode errorCode() {
    return error;
  }

  public int statusCode() {
    return this.error.statusCode();
  }

  public ErrorCode error() {
    return this.error;
  }


}
