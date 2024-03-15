package com.sb.calculator.service;

import com.sb.calculator.model.Item;
import com.sb.calculator.util.CalculatorUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.sb.calculator.util.CalculatorUtil.multiplyWithScale;
import static com.sb.calculator.util.CalculatorUtil.applyOffer;

public class PriceCalculator {

  final static public BiFunction<BigDecimal, Long, BigDecimal> basicPriceCalculator =
          CalculatorUtil::multiplyWithScale;

  final static public BiFunction<BigDecimal, Long, BigDecimal> buyOneGetOneFreePriceCalculator =
      (price, count) -> multiplyWithScale(price, applyOffer(count, 2, 1));

  final static public BiFunction<BigDecimal, Long, BigDecimal> threeForTwoPriceCalculator =
      (price, count) -> multiplyWithScale(price, applyOffer(count, 3, 2));

  private final static Map<String, Item> items= new HashMap<>();


 static {
     items.put("Apple", new Item("Apple", 35, basicPriceCalculator));
     items.put("Banana", new Item("Banana", 20, basicPriceCalculator));
     items.put("Melon", new Item("Melon", 50, buyOneGetOneFreePriceCalculator));
     items.put("Lime", new Item("Lime", 15, threeForTwoPriceCalculator));

  }


  private static Map<String, Long> groupItems(List<String> cart) {

    return cart.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
  }

  public static BigDecimal calculateTotalPrice(List<String> cart) {
    if(cart == null) {
        return BigDecimal.ZERO;
    }

    Map<String, Long> itemCounts = groupItems(cart);

   return  itemCounts.entrySet().stream().filter(e -> items.containsKey(e.getKey()))
       .map(e -> calculateDiscountedPrice(e.getKey(),e.getValue())).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
  }


  private static BigDecimal calculateDiscountedPrice(String key, Long count){
     Item item = items.get(key);
     return item.getCalculator().apply(item.getPrice(),count);
  }

}