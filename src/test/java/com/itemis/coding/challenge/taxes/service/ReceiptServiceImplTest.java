package com.itemis.coding.challenge.taxes.service;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import com.itemis.coding.challenge.taxes.model.Item;
import com.itemis.coding.challenge.taxes.model.Origin;
import com.itemis.coding.challenge.taxes.model.PreferentialTax;
import com.itemis.coding.challenge.taxes.model.Receipt;
import com.itemis.coding.challenge.taxes.model.ShopCart;
import com.itemis.coding.challenge.taxes.repository.ItemRepository;
import com.itemis.coding.challenge.taxes.repository.ItemRepositoryImpl;

public class ReceiptServiceImplTest {
  private ItemRepository itemRepository;
  private Item book;
  private Item musicCd;
  private Item chocolateBar;
  private final PrintStream originalOut = System.out;
  private ByteArrayOutputStream outContent;

  @BeforeEach
  public void initEach() {
    book = new Item(1, "book", 12.49, Origin.DOMESTIC, PreferentialTax.BOOK);
    musicCd = new Item(2, "music CD", 14.99, Origin.DOMESTIC, PreferentialTax.OTHER);
    chocolateBar = new Item(3, "chocolate bar", 0.85, Origin.DOMESTIC, PreferentialTax.FOOD);
    itemRepository = new ItemRepositoryImpl(List.of(book, musicCd, chocolateBar));
    outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  public void cleanUpEach() {
    System.setOut(originalOut);
  }

  @Test
  @Tag("ReceiptServiceImplTest")
  @EnabledOnOs({OS.WINDOWS, OS.LINUX})
  @DisplayName("Print Receipt")
  public void whenPrintReceiptShouldFulfillPrinting() {
    ItemTaxService itemTaxService = new ItemTaxServiceImpl(10, 5, 0.05);
    ShopCart shopCart = new ShopCart();
    shopCart.getItemToQuantity().put(book, 1);
    shopCart.getItemToQuantity().put(musicCd, 1);
    shopCart.getItemToQuantity().put(chocolateBar, 1);

    Receipt receipt = itemTaxService.calculateTaxes(shopCart);
    ReceiptService receiptService = new ReceiptServiceImpl(receipt,
      new ShopCartServiceImpl(itemRepository, shopCart));
    receiptService.printReceipt();

    assertThat(outContent.toString(), CoreMatchers.allOf(
      containsString("> 1 book: 12.49"),
      containsString("> 1 music CD: 16.49"),
      containsString("> 1 chocolate bar: 0.85"),
      containsString("> Sales Taxes: 1.50"),
      containsString("> Total: 29.83")));
  }
}
