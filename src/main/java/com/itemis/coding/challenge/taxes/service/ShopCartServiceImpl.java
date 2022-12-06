package com.itemis.coding.challenge.taxes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itemis.coding.challenge.taxes.model.ShopCart;
import com.itemis.coding.challenge.taxes.repository.ItemRepository;

@Service
public class ShopCartServiceImpl implements ShopCartService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ShopCartServiceImpl.class);
  private final ItemRepository itemRepository;
  private final ShopCart shopCart;

  public ShopCartServiceImpl(ItemRepository itemRepository, ShopCart shopCart) {
    this.itemRepository = itemRepository;
    this.shopCart = shopCart;
  }

  @Override
  public void printAllItems() {

  }

  @Override
  public void printCartItems() {

  }

  @Override
  public void printItemsByIds(long... id) {

  }

  @Override
  public void addItemToShopCart(long id, int quantity) {

  }

  @Override
  public ShopCart getShopCart() {
    return shopCart;
  }
}
