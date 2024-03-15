package com.sb.calculator.model;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public class Item {

  private final BigDecimal price;
  private final String name;
  private final BiFunction<BigDecimal, Long, BigDecimal> calculator;


  public Item(String name, int price, BiFunction<BigDecimal, Long, BigDecimal> calculator) {
    this.name = name;
    this.price = new BigDecimal(price);
    this.calculator = calculator;
  }


  public BigDecimal getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }

  public BiFunction<BigDecimal, Long, BigDecimal> getCalculator() {
    return calculator;
  }
}