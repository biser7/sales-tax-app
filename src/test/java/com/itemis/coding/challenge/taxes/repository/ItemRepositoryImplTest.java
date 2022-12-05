package com.itemis.coding.challenge.taxes.repository;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemRepositoryImplTest {
  private ItemRepository itemRepository;

  @BeforeEach
  public void initEach() {
    itemRepository = new ItemRepositoryImpl(new ArrayList<>());
  }

  @Test
  public void whenCallGetItemListShouldReturnNotNull() {
    Assertions.assertNotNull(itemRepository.getAllItems());
  }
}