package com.itemis.coding.challenge.taxes.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.itemis.coding.challenge.taxes.model.Item;
import com.itemis.coding.challenge.taxes.model.Origin;
import com.itemis.coding.challenge.taxes.model.PreferentialTax;
import com.itemis.coding.challenge.taxes.model.Receipt;
import com.itemis.coding.challenge.taxes.model.ShopCart;

public class ItemTaxServiceImplTest {
  private Item book;
  private Item musicCd;
  private Item chocolateBar;

  @BeforeEach
  public void initEach() {
    book = new Item(1, "book", 12.49, Origin.DOMESTIC, PreferentialTax.BOOK);
    musicCd = new Item(2, "music CD", 14.99, Origin.DOMESTIC, PreferentialTax.OTHER);
    chocolateBar = new Item(3, "chocolate bar", 0.85, Origin.DOMESTIC, PreferentialTax.FOOD);
  }

  @Test
  public void whenCalculateTaxesForFirstInputShouldReturnFirstReceipt() {
    ItemTaxService itemTaxService = new ItemTaxServiceImpl(10,5,0.05);
    ShopCart shopCart = new ShopCart();
    shopCart.getItemToQuantity().put(book, 1);
    shopCart.getItemToQuantity().put(musicCd, 1);
    shopCart.getItemToQuantity().put(chocolateBar, 1);

    Receipt receipt = itemTaxService.calculateTaxes(shopCart);

    Assertions.assertEquals(1.50, receipt.getSaleTax(), 0.0001);
    Assertions.assertEquals(29.83, receipt.getTotalAmount(), 0.0001);
    Assertions.assertEquals(shopCart, receipt.getShopCart());
    Assertions.assertEquals(12.49, receipt.getItemToAmount().get(book), 0.0001);
    Assertions.assertEquals(16.49, receipt.getItemToAmount().get(musicCd), 0.0001);
    Assertions.assertEquals(0.85, receipt.getItemToAmount().get(chocolateBar), 0.0001);
  }
}