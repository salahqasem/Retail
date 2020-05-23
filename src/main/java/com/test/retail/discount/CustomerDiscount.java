package com.test.retail.discount;

public class CustomerDiscount extends DiscountBase {

    @Override
    double calculateDiscount(double total) {
        return total * 0.05;
    }
}
