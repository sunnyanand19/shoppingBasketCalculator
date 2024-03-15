package com.sb.calculator.util;

import java.math.BigDecimal;

public class CalculatorUtil {

  private static final int SCALE = 2;

  private CalculatorUtil() {
  }

  public static long applyOffer(long num, long requiredQuantity,long discountedQuantity) {
    return ((num / requiredQuantity) ) * discountedQuantity + (num % requiredQuantity);
  }

  public static BigDecimal multiplyWithScale(BigDecimal price, long count) {
    return  new BigDecimal(count).multiply(price).setScale(SCALE);
  }


}