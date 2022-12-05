package com.itemis.coding.challenge.taxes;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itemis.coding.challenge.taxes.model.Item;
import com.itemis.coding.challenge.taxes.model.Origin;
import com.itemis.coding.challenge.taxes.model.PreferentialTax;
import com.itemis.coding.challenge.taxes.repository.ItemRepositoryImpl;

@Configuration
public class TaxesAppConfiguration {

  @Bean
  public Item item() {
    return new Item();
  }

  @Bean
  public ItemRepositoryImpl itemRepository() {
    Item book = new Item(1, "book", 12.49, Origin.DOMESTIC, PreferentialTax.BOOK);
    Item musicCd = new Item(2, "music CD", 14.99, Origin.DOMESTIC, PreferentialTax.OTHER);
    Item chocolateBar = new Item(3, "chocolate bar", .85, Origin.DOMESTIC, PreferentialTax.FOOD);
    return new ItemRepositoryImpl(List.of(book, musicCd, chocolateBar));
  }
}
