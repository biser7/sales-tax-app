package com.itemis.coding.challenge.taxes.service;

import java.util.Locale;

import org.springframework.stereotype.Service;

import com.itemis.coding.challenge.taxes.model.Receipt;

@Service
public class ReceiptServiceImpl implements ReceiptService {
  private final Locale RECEIPT_LOCALE = Locale.US;
  private final Receipt receipt;
  private final ShopCartService shopCartService;

  public ReceiptServiceImpl(Receipt receipt, ShopCartService shopCartService) {
    this.receipt = receipt;
    this.shopCartService = shopCartService;
  }

  @Override
  public void printReceipt() {
    System.out.println("Client Receipt:");
    receipt
      .getShopCart()
      .getItemToQuantity()
      .forEach((item, quantity) -> System.out.printf(RECEIPT_LOCALE, "> %d %s: %.2f\n", quantity,
        item.getName(), receipt.getItemAmount(item)));
    System.out.printf(RECEIPT_LOCALE, "> Sales Taxes: %.2f\n", receipt.getSaleTax());
    System.out.printf(RECEIPT_LOCALE, "> Total: %.2f\n", receipt.getTotalAmount());

    cleanShopCart();
  }

  private void cleanShopCart() {
    shopCartService.getShopCart().getItemToQuantity().clear();
  }
}
