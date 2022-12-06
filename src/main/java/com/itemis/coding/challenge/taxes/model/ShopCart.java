package com.itemis.coding.challenge.taxes.model;

import java.util.HashMap;
import java.util.Map;

public class ShopCart {
  private Map<Item, Integer> itemToQuantity;

  public ShopCart() {
    this.itemToQuantity = new HashMap<>();
  }

  public Map<Item, Integer> getItemToQuantity() {
    return itemToQuantity;
  }

  public void setItemToQuantity(Map<Item, Integer> itemToQuantity) {
    this.itemToQuantity = itemToQuantity;
  }

  public boolean isShopCartContainItem(long id) {
    return itemToQuantity
      .keySet()
      .stream()
      .anyMatch(i -> i.getId() == id);
  }

  public Map.Entry<Item, Integer> getItemEntryById(long id) {
    return itemToQuantity
      .entrySet()
      .stream()
      .filter(set -> set.getKey().getId() == id)
      .findFirst()
      .orElseThrow(() -> new RuntimeException(String.format("Not found Item with id: %d", id)));
  }
}
