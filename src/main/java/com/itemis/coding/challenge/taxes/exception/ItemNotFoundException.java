package com.itemis.coding.challenge.taxes.exception;

public class ItemNotFoundException extends IllegalArgumentException {
  public ItemNotFoundException(String s) {
    super(s);
  }

  public ItemNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}