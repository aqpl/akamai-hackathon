package com.hackathon.hackathon;

public enum ErrorCode {

  InternalServerError(500),
  InvalidRequest(400);

  int statusCode;

  ErrorCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public int statusCode() {
    return statusCode;
  }
}

