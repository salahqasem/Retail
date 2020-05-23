package com.test.retail.discount;

import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class DiscountStrategyTest {

    @Test
    public void shouldReturnEmployeeDiscount() {
        Discount discount = DiscountStrategy.getDiscountStrategy("Employee", new Date());
        assertTrue(discount instanceof EmployeeDiscount);
    }

    @Test
    public void shouldReturnAffiliateDiscount() {
        Discount discount = DiscountStrategy.getDiscountStrategy("Affiliate", new Date());
        assertTrue(discount instanceof AffiliateDiscount);
    }

    @Test
    public void shouldReturnCustomerDiscountForOldCustomer() {
        Discount discount = DiscountStrategy.getDiscountStrategy("Customer",
            new GregorianCalendar(2015, 11, 11).getTime());
        assertTrue(discount instanceof CustomerDiscount);
    }

    @Test
    public void shouldReturnNoopDiscountForNewCustomer() {
        Discount discount = DiscountStrategy.getDiscountStrategy("Customer", new Date());
        assertTrue(discount instanceof NoopDiscount);
    }

    @Test
    public void shouldReturnNoopDiscountForAnonymousUser() {
        Discount discount = DiscountStrategy.getDiscountStrategy("IDK", new Date());
        assertTrue(discount instanceof NoopDiscount);
    }

}
