package com.sb.calculator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CalculatorUtilTest {

    @ParameterizedTest
    @MethodSource("offerParameters")
    public void testApplyOffer(long num, long requiredQuantity, long discountedQuantity, long expected) {
        long result = CalculatorUtil.applyOffer(num, requiredQuantity, discountedQuantity);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("multiplicationParameters")
    public void testMultiplyWithScale(BigDecimal price, long count, BigDecimal expected) {
        BigDecimal result = CalculatorUtil.multiplyWithScale(price, count);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> offerParameters() {
        return Stream.of(
                Arguments.of(10L, 3L, 2L, 7L),
                Arguments.of(10L, 2L, 1L, 5L)
        );
    }

    private static Stream<Arguments> multiplicationParameters() {
        return Stream.of(
                Arguments.of(new BigDecimal("2.50"), 3L, new BigDecimal("7.50")),
                Arguments.of(new BigDecimal("3.00"), 4L, new BigDecimal("12.00"))
        );
    }
}
