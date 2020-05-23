package com.test.retail.discount;

public abstract class DiscountBase implements Discount {

    @Override
    public double doDiscount(double total) {
        validateTotal(total);
        return calculateDiscount(total);
    }

    abstract double calculateDiscount(double total);

    void validateTotal(double total) {
        if(total < 0) {
            throw new IllegalArgumentException("the total cant be less than zero");
        }
    }

}
