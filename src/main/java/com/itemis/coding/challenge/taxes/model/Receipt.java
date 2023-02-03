package com.itemis.coding.challenge.taxes.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Receipt {
  private ShopCart shopCart;
  private Map<Item, Double> itemToAmount;
  private double saleTax;
  private double totalAmount;

  public Receipt(ShopCart shopCart) {
    this.shopCart = shopCart;
    this.itemToAmount = new HashMap<>();
  }

  public double getItemAmount(Item item) {
    return itemToAmount.get(item);
  }
}
