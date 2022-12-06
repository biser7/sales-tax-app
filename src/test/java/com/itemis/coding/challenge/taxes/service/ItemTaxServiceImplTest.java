package com.itemis.coding.challenge.taxes.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
  private Item importedBoxOfChocolates;
  private Item importedBottleOfPerfume;
  private Item importedBottleOfPerfume1;
  private Item bottleOfPerfume;
  private Item packetOfHeadachePills;
  private Item boxOfImportedChocolates;

  @BeforeEach
  public void initEach() {
    book = new Item(1, "book", 12.49, Origin.DOMESTIC, PreferentialTax.BOOK);
    musicCd = new Item(2, "music CD", 14.99, Origin.DOMESTIC, PreferentialTax.OTHER);
    chocolateBar = new Item(3, "chocolate bar", 0.85, Origin.DOMESTIC, PreferentialTax.FOOD);
    importedBoxOfChocolates = new Item(4, " imported box of chocolates", 10.00, Origin.IMPORT, PreferentialTax.FOOD);
    importedBottleOfPerfume = new Item(5, "imported bottle of perfume", 47.50, Origin.IMPORT, PreferentialTax.OTHER);
    importedBottleOfPerfume1 = new Item(6, "imported bottle of perfume 1", 27.99, Origin.IMPORT, PreferentialTax.OTHER);
    bottleOfPerfume = new Item(7, "bottle of perfume", 18.99, Origin.DOMESTIC, PreferentialTax.OTHER);
    packetOfHeadachePills = new Item(8, "packet of headache pills", 9.75, Origin.DOMESTIC, PreferentialTax.MEDICATION);
    boxOfImportedChocolates = new Item(9, "box of imported chocolates", 11.25, Origin.IMPORT, PreferentialTax.FOOD);
  }

  @Test
  @Tag("ItemTaxServiceImplTest")
  @DisplayName("Calculate receipt for the first items list")
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

  @Test
  @Tag("ItemTaxServiceImplTest")
  @DisplayName("Calculate receipt for the second items list")
  public void whenCalculateTaxesForSecondInputShouldReturnSecondReceipt() {
    ItemTaxService itemTaxService = new ItemTaxServiceImpl(10,5,0.05);
    ShopCart shopCart = new ShopCart();
    shopCart.getItemToQuantity().put(importedBoxOfChocolates, 1);
    shopCart.getItemToQuantity().put(importedBottleOfPerfume, 1);

    Receipt receipt = itemTaxService.calculateTaxes(shopCart);

    Assertions.assertEquals(7.65, receipt.getSaleTax(), 0.0001);
    Assertions.assertEquals(65.15, receipt.getTotalAmount(), 0.0001);
    Assertions.assertEquals(shopCart, receipt.getShopCart());
    Assertions.assertEquals(10.50, receipt.getItemToAmount().get(importedBoxOfChocolates), 0.0001);
    Assertions.assertEquals(54.65, receipt.getItemToAmount().get(importedBottleOfPerfume), 0.0001);
  }

  @Test
  @Tag("ItemTaxServiceImplTest")
  @DisplayName("Calculate receipt for the third items list")
  public void whenCalculateTaxesForThirdInputShouldReturnThirdReceipt() {
    ItemTaxService itemTaxService = new ItemTaxServiceImpl(10,5,0.05);
    ShopCart shopCart = new ShopCart();
    shopCart.getItemToQuantity().put(importedBottleOfPerfume1, 1);
    shopCart.getItemToQuantity().put(bottleOfPerfume, 1);
    shopCart.getItemToQuantity().put(packetOfHeadachePills, 1);
    shopCart.getItemToQuantity().put(boxOfImportedChocolates, 1);

    Receipt receipt = itemTaxService.calculateTaxes(shopCart);

    Assertions.assertEquals(6.7, receipt.getSaleTax(), 0.0001);
    Assertions.assertEquals(74.68, receipt.getTotalAmount(), 0.0001);
    Assertions.assertEquals(shopCart, receipt.getShopCart());
    Assertions.assertEquals(32.19, receipt.getItemToAmount().get(importedBottleOfPerfume1), 0.0001);
    Assertions.assertEquals(20.89, receipt.getItemToAmount().get(bottleOfPerfume), 0.0001);
    Assertions.assertEquals(9.75, receipt.getItemToAmount().get(packetOfHeadachePills), 0.0001);
    Assertions.assertEquals(11.85, receipt.getItemToAmount().get(boxOfImportedChocolates), 0.0001);
  }
}