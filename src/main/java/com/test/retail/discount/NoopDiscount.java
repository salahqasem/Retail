package com.test.retail.discount;

public class NoopDiscount extends DiscountBase {

    @Override
    double calculateDiscount(double total) {
        return 0;
    }

    @Override
    void validateTotal(double total) {
        //do nothing, it's a noop :D
    }
}
