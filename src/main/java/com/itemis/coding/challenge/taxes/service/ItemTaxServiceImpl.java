package com.itemis.coding.challenge.taxes.service;

import org.springframework.beans.factory.annotation.Value;

import com.itemis.coding.challenge.taxes.model.Item;
import com.itemis.coding.challenge.taxes.model.Origin;
import com.itemis.coding.challenge.taxes.model.PreferentialTax;
import com.itemis.coding.challenge.taxes.model.Receipt;
import com.itemis.coding.challenge.taxes.model.ShopCart;

public class ItemTaxServiceImpl implements ItemTaxService{
  private final double salesTax;
  private final double importDuty;
  private final double roundingPrecision;

  public ItemTaxServiceImpl(@Value("${sales.tax.percent.value}") double salesTax,
    @Value("${import.duty.percent.value}") double importDuty,
    @Value("${rounding.precision.value}") double roundingPrecision) {

    this.salesTax = salesTax;
    this.importDuty = importDuty;
    this.roundingPrecision = roundingPrecision;
  }

  @Override
  public Receipt calculateTaxes(ShopCart shopCart) {
    Receipt receipt = new Receipt(shopCart);
    shopCart.getItemToQuantity().forEach((item, quantity) -> {
      double salesTaxAmount = getSalesTaxAmount(item);
      double importDutyAmount = getImportDutyAmount(item);

      receipt.setSaleTax((receipt.getSaleTax() + salesTaxAmount + importDutyAmount) * quantity);
      receipt.setTotalAmount(receipt.getTotalAmount()
        + (item.getNetPrice() + salesTaxAmount + importDutyAmount ) * quantity);

      receipt.getItemToAmount().put(item, (item.getNetPrice() + salesTaxAmount + importDutyAmount)
        * quantity);
    });

    return receipt;
  }

  private double getImportDutyAmount(Item item) {
    double importDutyAmount = 0;
    if(item.getOrigin().equals(Origin.IMPORT)) {
      importDutyAmount = roundingPrecision * (Math.ceil(Math.abs(item.getNetPrice() * importDuty / 100 / roundingPrecision)));
    }
    return importDutyAmount;
  }

  private double getSalesTaxAmount(Item item) {
    double salesTaxAmount = 0;
    if(item.getPreferentialTax().equals(PreferentialTax.OTHER)) {
      salesTaxAmount = roundingPrecision * (Math.ceil(Math.abs(item.getNetPrice() * salesTax / 100 / roundingPrecision)));
    }
    return salesTaxAmount;
  }
}
