package com.test.retail.discount;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

public class AffiliateDiscountTest {

    Discount discount = new AffiliateDiscount();

    @Test
    public void shouldDo10PercentDiscount() {
        assertThat(discount.doDiscount(100), is(10.0));
    }

    @Test
    public void IllegalArgExceptionShouldBeThrownForNegativeAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> discount.doDiscount(-10));
        assertThat(exception.getMessage(), is("the total cant be less than zero"));
    }
}
