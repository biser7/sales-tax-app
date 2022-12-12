package com.itemis.coding.challenge.taxes.service;

import com.itemis.coding.challenge.taxes.model.ShopCart;

public interface ShopCartService {
  void printAllItems();

  void printCartItems();

  void printItemsByIds(long... id);

  void addItemToShopCart(long id, int quantity);

  ShopCart getShopCart();
}
