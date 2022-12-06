package com.itemis.coding.challenge.taxes.service;

import org.springframework.stereotype.Service;

import com.itemis.coding.challenge.taxes.model.Receipt;

@Service
public class ReceiptServiceImpl implements ReceiptService {
  private final Receipt receipt;

  public ReceiptServiceImpl(Receipt receipt) {
    this.receipt = receipt;
  }

  @Override
  public void printReceipt() {
    System.out.println("Client Receipt:");
    receipt
      .getShopCart()
      .getItemToQuantity()
      .forEach((item, quantity) -> System.out.printf("> %d %s: %.2f\n", quantity, item.getName(),
        receipt.getItemAmount(item)));
    System.out.printf("> Sales Taxes: %.2f\n", receipt.getSaleTax());
    System.out.printf("> Total: %.2f\n", receipt.getTotalAmount());
  }
}
