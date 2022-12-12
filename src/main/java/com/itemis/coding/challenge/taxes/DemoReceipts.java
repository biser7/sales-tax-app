package com.itemis.coding.challenge.taxes;

import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itemis.coding.challenge.taxes.exception.ItemNotFoundException;
import com.itemis.coding.challenge.taxes.service.ItemTaxService;
import com.itemis.coding.challenge.taxes.service.ReceiptService;
import com.itemis.coding.challenge.taxes.service.ReceiptServiceImpl;
import com.itemis.coding.challenge.taxes.service.ShopCartService;

@Component
public class DemoReceipts {
  private static final Logger LOGGER = LoggerFactory.getLogger(DemoReceipts.class);
  private static final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8);
  private final ItemTaxService itemTaxService;
  private final ShopCartService shopCartService;
  private ReceiptService receiptService;

  public DemoReceipts(ItemTaxService itemTaxService, ShopCartService shopCartService) {
    this.itemTaxService = itemTaxService;
    this.shopCartService = shopCartService;
  }

  public void runApp() {
    boolean running = true;
    while (running) {
      System.out.println("Hello! To print app helper, enter: 0");
      try {
        int modeChoice = SCANNER.nextInt();
        switch (modeChoice) {
          case 0 -> printHelper();
          case 1 -> shopCartService.printAllItems();
          case 2 -> addProductToCart();
          case 3 -> setFirstTaskInput();
          case 4 -> setSecondTaskInput();
          case 5 -> setThirdTaskInput();
          case 6 -> shopCartService.printCartItems();
          case 7 -> {
            receiptService = new ReceiptServiceImpl(itemTaxService
              .calculateTaxes(shopCartService.getShopCart()), shopCartService);
            receiptService.printReceipt();
          }
          case 8 -> running = false;
          default -> System.out.println("Make correct choice!");
        }
      } catch (InputMismatchException | IllegalStateException e) {
        LOGGER.error("Error! Enter integers only, please [1-8]", e);
        SCANNER.nextLine();
      }
    }
    SCANNER.close();
  }

  private void addProductToCart() {
    try {
      System.out.print("Enter item id: ");
      int itemId = SCANNER.nextInt();
      if (itemId <= 0 || itemId > 8) {
        throw new ItemNotFoundException("Not correct item Id!");
      }
      System.out.print("Enter item quantity: ");
      int itemQuantity = SCANNER.nextInt();
      if (itemQuantity <= 0) {
        throw new ItemNotFoundException("Not correct item quantity!");
      }
      shopCartService.addItemToShopCart(itemId, itemQuantity);
    } catch (InputMismatchException | ItemNotFoundException e) {
      LOGGER.error("Integers only, please. Error: {}", e.getMessage());
      SCANNER.nextLine();
    }
  }

  private void setFirstTaskInput() {
    shopCartService.addItemToShopCart(1, 1);
    shopCartService.addItemToShopCart(2, 1);
    shopCartService.addItemToShopCart(3, 1);
  }

  private void setSecondTaskInput() {
    shopCartService.addItemToShopCart(4, 1);
    shopCartService.addItemToShopCart(5, 1);
  }

  private void setThirdTaskInput() {
    shopCartService.addItemToShopCart(6, 1);
    shopCartService.addItemToShopCart(7, 1);
    shopCartService.addItemToShopCart(8, 1);
    shopCartService.addItemToShopCart(9, 1);
  }

  private void printHelper() {
    System.out.println("""
      Pls, choose the following options:
      <1> - print products catalogue
      <2> - add product to shop cart
      <3> - add first task input to cart
      <4> - add second task input to cart
      <5> - add third task input to cart
      <6> - print shop cart content
      <7> - print receipt
      <8> - close application
      ---------------------------------------------------------""");
  }
}