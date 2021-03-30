package com.tms.task.exception;

public class RecordNotFoundException extends RuntimeException {

  public RecordNotFoundException(String message) {
    super(message);
  }

  public RecordNotFoundException(String message, Throwable throwable) {
    super(message, throwable);
  }
}

