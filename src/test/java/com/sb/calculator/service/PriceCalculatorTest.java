package com.sb.calculator.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceCalculatorTest {

    @ParameterizedTest
    @MethodSource("cartDataProvider")
    void testCalculateTotalPrice(List<String> cart, BigDecimal expectedTotalPrice) {
        BigDecimal totalPrice = PriceCalculator.calculateTotalPrice(cart);
        assertEquals(expectedTotalPrice, totalPrice);
    }

    private static Stream<Arguments> cartDataProvider() {
        return Stream.of(
                Arguments.of(Arrays.asList("Apple", "Banana", "Melon", "Lime"), new BigDecimal("120.00")),
                Arguments.of(Arrays.asList("Apple", "Apple", "Banana", "Melon", "Melon", "Lime"), new BigDecimal("155.00")),
                Arguments.of(Arrays.asList("Apple", "Banana", "Melon", "Lime", "Lime", "Lime", "Lime"), new BigDecimal("150.00")),
                Arguments.of(Arrays.asList("Banana", "Banana", "Banana", "Banana", "Banana"), new BigDecimal("100.00")),
                Arguments.of(Arrays.asList("Lime", "Lime", "Lime"), new BigDecimal("30.00")),
                Arguments.of(List.of(), BigDecimal.ZERO),
                Arguments.of(null, BigDecimal.ZERO)
        );
    }
}
