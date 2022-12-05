package com.itemis.coding.challenge.taxes.repository;

import java.util.List;

import com.itemis.coding.challenge.taxes.model.Item;

public class ItemRepositoryImpl implements ItemRepository{
  private List<Item> itemsList;

  public ItemRepositoryImpl() {
  }

  public ItemRepositoryImpl(List<Item> itemsList) {
    this.itemsList = itemsList;
  }

  @Override
  public List<Item> getAllItems() {
    return itemsList;
  }

  @Override
  public Item getItemById(long id) {
    return null;
  }
}
