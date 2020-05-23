package com.test.retail.discount;

public class AffiliateDiscount extends DiscountBase {


    @Override
    double calculateDiscount(double total) {
        return total * .1;
    }
}
