package com.test.retail.discount;

public class NoopDiscount extends DiscountBase {

    @Override
    double calculateDiscount(double total) {
        return 0;
    }

    @Override
    void validateTotal(double total) {
    }
}
