package com.itemis.coding.challenge.taxes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itemis.coding.challenge.taxes.model.Item;
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
    System.out.println("Products catalogue:");
    itemRepository.getAllItems().forEach(System.out::println);
  }

  @Override
  public void printCartItems() {

  }

  @Override
  public void printItemsByIds(long... id) {

  }

  @Override
  public void addItemToShopCart(long id, int quantity) {
    Item item = itemRepository.getItemById(id);
    if (shopCart.isShopCartContainItem(item.getId())) {
      shopCart.getItemToQuantity().put(item, shopCart.getItemToQuantity().get(item) + quantity);
    } else {
      shopCart.getItemToQuantity().put(item, quantity);
    }
  }

  @Override
  public ShopCart getShopCart() {
    return shopCart;
  }
}
