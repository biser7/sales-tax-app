package com.itemis.coding.challenge.taxes.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.itemis.coding.challenge.taxes.model.Item;
import com.itemis.coding.challenge.taxes.model.Origin;
import com.itemis.coding.challenge.taxes.model.PreferentialTax;
import com.itemis.coding.challenge.taxes.model.Receipt;
import com.itemis.coding.challenge.taxes.model.ShopCart;
import com.itemis.coding.challenge.taxes.repository.ItemRepository;
import com.itemis.coding.challenge.taxes.repository.ItemRepositoryImpl;

@Configuration
public class TaxesAppConfiguration {
  @Autowired
  private Environment env;

  @Bean
  public Item item() {
    return new Item();
  }

  @Bean
  public ItemRepository itemRepository() {
    Item book = new Item(1, "book", env.getProperty("book.price", double.class),
      Origin.DOMESTIC, PreferentialTax.BOOK);
    Item musicCd = new Item(2, "music CD", env.getProperty("musicCd.price", double.class),
      Origin.DOMESTIC, PreferentialTax.OTHER);
    Item chocolateBar = new Item(3, "chocolate bar", env.getProperty("chocolate.bar.price", double.class),
      Origin.DOMESTIC, PreferentialTax.FOOD);
    Item importedBoxOfChocolates = new Item(4, "imported box of chocolates",
      env.getProperty("imported.box.of.chocolates.price", double.class), Origin.IMPORT, PreferentialTax.FOOD);
    Item importedBottleOfPerfume = new Item(5, "imported bottle of perfume",
      env.getProperty("imported.bottle.of.perfume.price", double.class), Origin.IMPORT, PreferentialTax.OTHER);
    Item importedBottleOfPerfume1 = new Item(6, "imported bottle of perfume-1",
      env.getProperty("imported.bottle.of.perfume-1.price", double.class), Origin.IMPORT, PreferentialTax.OTHER);
    Item bottleOfPerfume = new Item(7, "bottle of perfume",
      env.getProperty("bottle.of.perfume.price", double.class), Origin.DOMESTIC, PreferentialTax.OTHER);
    Item packetOfHeadachePills = new Item(8, "packet of headache pills",
      env.getProperty("packet.of.headache.pills.price", double.class), Origin.DOMESTIC, PreferentialTax.MEDICATION);
    Item boxOfImportedChocolates = new Item(9, "box of imported chocolates",
      env.getProperty("box.of.imported.chocolates.price", double.class), Origin.IMPORT, PreferentialTax.FOOD);
    return new ItemRepositoryImpl(List.of(book, musicCd, chocolateBar, importedBoxOfChocolates,
      importedBottleOfPerfume, importedBottleOfPerfume1,
      bottleOfPerfume, packetOfHeadachePills, boxOfImportedChocolates));
  }

  @Bean
  public ShopCart shopCart() {
    return new ShopCart();
  }

  @Bean
  public Receipt receipt() {
    return new Receipt();
  }
}
