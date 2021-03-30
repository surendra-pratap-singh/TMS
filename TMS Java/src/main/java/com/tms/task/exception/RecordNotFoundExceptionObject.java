package com.tms.task.exception;

public class RecordNotFoundExceptionObject extends RuntimeException {

  public RecordNotFoundExceptionObject(String message) {
    super(message);
  }

  public RecordNotFoundExceptionObject(String message, Throwable throwable) {
    super(message, throwable);
  }
}
