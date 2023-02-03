package com.itemis.coding.challenge.taxes.service;

import java.util.stream.LongStream;

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
    if (shopCart.getItemToQuantity().isEmpty()) {
      System.out.println("Shop Cart is empty!");
      return;
    }
    System.out.println("Products in Shop cart:");
    shopCart.getItemToQuantity().forEach((i, q) -> System.out.printf("> %d %s at %.2f\n", q,
      i.getName(), i.getNetPrice()));
  }

  @Override
  public void printItemsByIds(long... id) {
    LongStream.of(id).forEach(i -> {
      if (shopCart.isShopCartContainItem(i)) {
        System.out.println(shopCart.getItemEntryById(i).getKey().toString() + " --> "
          + shopCart.getItemEntryById(i).getValue());
      } else {
        LOGGER.warn("Not found Item with id");
      }
    });
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
