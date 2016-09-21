package com.rlogman.guaranteedrate.sorttest;

public class InternalException extends Exception {

  private static final long serialVersionUID = -3183663395613980347L;

  public InternalException(String message) {
    super(message);
  }

  public InternalException(String message, Throwable cause) {
    super(message, cause);
  }

  public InternalException(Throwable cause) {
    super(cause);
  }
}
