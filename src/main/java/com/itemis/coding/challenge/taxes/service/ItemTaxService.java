package com.itemis.coding.challenge.taxes.service;

import com.itemis.coding.challenge.taxes.model.Receipt;
import com.itemis.coding.challenge.taxes.model.ShopCart;

public interface ItemTaxService {
  Receipt calculateTaxes(ShopCart shopCart);
}
