package com.itemis.coding.challenge.taxes.repository;

import java.util.List;

import com.itemis.coding.challenge.taxes.model.Item;

public interface ItemRepository {
  List<Item> getAllItems();

  Item getItemById(long id);
}
