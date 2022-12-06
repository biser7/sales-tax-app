package com.itemis.coding.challenge.taxes.service;

import com.itemis.coding.challenge.taxes.model.Receipt;

public class ReceiptServiceImpl implements ReceiptService {
  private final Receipt receipt;

  public ReceiptServiceImpl(Receipt receipt) {
    this.receipt = receipt;
  }

  @Override
  public void printReceipt() {

  }
}
