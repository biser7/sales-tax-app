package com.itemis.coding.challenge.taxes.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.itemis.coding.challenge.taxes.model.Item;
import com.itemis.coding.challenge.taxes.model.Origin;
import com.itemis.coding.challenge.taxes.model.PreferentialTax;

public class ItemRepositoryImplTest {
  private ItemRepository itemRepository;
  private Item book;
  private Item musicCd;
  private Item chocolateBar;

  @BeforeEach
  public void initEach() {
    book = new Item(1, "book", 12.49, Origin.DOMESTIC, PreferentialTax.BOOK);
    musicCd = new Item(2, "music CD", 14.99, Origin.DOMESTIC, PreferentialTax.OTHER);
    chocolateBar = new Item(3, "chocolate bar", .85, Origin.DOMESTIC, PreferentialTax.FOOD);
    itemRepository = new ItemRepositoryImpl(List.of(book, musicCd, chocolateBar));
  }

  @Test
  public void whenCallGetItemListShouldReturnNotNull() {
    Assertions.assertNotNull(itemRepository.getAllItems());
  }

  @Test
  public void whenCallGetItemListShouldReturnItemsList() {
    List<Item> items = itemRepository.getAllItems();

    Assertions.assertEquals(3, items.size());
    Assertions.assertEquals(book.getName(), items.get(0).getName());
    Assertions.assertEquals(book.getNetPrice(), items.get(0).getNetPrice());
    Assertions.assertSame(book.getOrigin(), items.get(0).getOrigin());
    Assertions.assertSame(book.getPreferentialTax(), items.get(0).getPreferentialTax());
    Assertions.assertEquals(musicCd.getName(), items.get(1).getName());
    Assertions.assertEquals(chocolateBar.getName(), items.get(2).getName());
  }
}
