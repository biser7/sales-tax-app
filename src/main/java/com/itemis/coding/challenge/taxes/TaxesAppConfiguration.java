package com.itemis.coding.challenge.taxes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itemis.coding.challenge.taxes.model.Item;
import com.itemis.coding.challenge.taxes.repository.ItemRepositoryImpl;

@Configuration
public class TaxesAppConfiguration {

  @Bean
  public Item item() {
    return new Item();
  }

  @Bean
  public ItemRepositoryImpl itemRepository() {
    return new ItemRepositoryImpl();
  }
}
