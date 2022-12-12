package com.itemis.coding.challenge.taxes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
  private long id;
  private String name;
  private double netPrice;
  private Origin origin;
  private PreferentialTax preferentialTax;
}
