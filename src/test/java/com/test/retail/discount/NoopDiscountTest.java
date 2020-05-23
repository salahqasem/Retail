package com.test.retail.discount;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

public class NoopDiscountTest {
    Discount discount = new NoopDiscount();

    @Test
    public void shouldDo10PercentDiscount() {
        assertThat(discount.doDiscount(100), is(0.0));
    }

    @Test
    public void NoIllegalArgExceptionShouldBeThrownForNegativeAmount() {
        assertThat(discount.doDiscount(-70), is(0.0));
    }
}
