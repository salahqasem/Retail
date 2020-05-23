package com.test.retail.discount;

public class HundredsDiscount extends DiscountBase {

    @Override
    double calculateDiscount(double total) {
        int sumOfHundreds = (int) total/100;
        return sumOfHundreds * 5;
    }
}
