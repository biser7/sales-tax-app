package com.itemis.coding.challenge.taxes.service;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.itemis.coding.challenge.taxes.model.Item;
import com.itemis.coding.challenge.taxes.model.Origin;
import com.itemis.coding.challenge.taxes.model.PreferentialTax;
import com.itemis.coding.challenge.taxes.model.ShopCart;
import com.itemis.coding.challenge.taxes.repository.ItemRepository;
import com.itemis.coding.challenge.taxes.repository.ItemRepositoryImpl;

public class ShopCartServiceImplTest {
  private ItemRepository itemRepository;
  private Item book;
  private Item musicCd;
  private Item chocolateBar;

  @BeforeEach
  public void initEach() {
    book = new Item(1, "book", 12.49, Origin.DOMESTIC, PreferentialTax.BOOK);
    musicCd = new Item(2, "music CD", 14.99, Origin.DOMESTIC, PreferentialTax.OTHER);
    chocolateBar = new Item(3, "chocolate bar", 0.85, Origin.DOMESTIC, PreferentialTax.FOOD);
    itemRepository = new ItemRepositoryImpl(List.of(book, musicCd, chocolateBar));
  }

  @Test
  public void whenGetShopCartShouldReturnShopCart() {
    ShopCartService shopCartService = new ShopCartServiceImpl(itemRepository, new ShopCart());

    Assertions.assertNotNull(shopCartService.getShopCart());
    Assertions.assertEquals(ShopCart.class, shopCartService.getShopCart().getClass());
  }

  @Test
  public void whenAddItemToShopCartThenShopCartContainsAddedItems() {
    ShopCartService shopCartService = new ShopCartServiceImpl(itemRepository, new ShopCart());

    shopCartService.addItemToShopCart(book.getId(), 1);
    shopCartService.addItemToShopCart(musicCd.getId(), 2);
    shopCartService.addItemToShopCart(chocolateBar.getId(), 3);
    shopCartService.addItemToShopCart(book.getId(), 1);
    ShopCart shopCart = shopCartService.getShopCart();

    Assertions.assertEquals(3, shopCart.getItemToQuantity().size());
    Assertions.assertEquals(2, (int) shopCart.getItemToQuantity().get(book));
    Assertions.assertEquals(2, (int) shopCart.getItemToQuantity().get(musicCd));
    Assertions.assertEquals(3, (int) shopCart.getItemToQuantity().get(chocolateBar));
  }

  @Test
  public void whenPrintAllItemsShouldPrintAllCartItems() {
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    final PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    ShopCartService shopCartService = new ShopCartServiceImpl(itemRepository, new ShopCart());
    shopCartService.addItemToShopCart(book.getId(), 1);
    shopCartService.addItemToShopCart(chocolateBar.getId(), 3);

    shopCartService.printAllItems();

    assertThat(outContent.toString(), CoreMatchers.allOf(
      containsString(book.toString()),
      containsString(chocolateBar.toString())));
    System.setOut(originalOut);
  }

  @Test
  public void whenPrintAllItemsAndCartIsEmptyShouldPrintSpecificSentence() {
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    final PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));
    ShopCartService shopCartService = new ShopCartServiceImpl(itemRepository, new ShopCart());

    shopCartService.printCartItems();

    assertThat(outContent.toString(), CoreMatchers.allOf(
      containsString("Shop Cart is empty!")));
    System.setOut(originalOut);
  }
}
