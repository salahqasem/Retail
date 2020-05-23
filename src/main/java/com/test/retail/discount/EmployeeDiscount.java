package com.test.retail.discount;

public class EmployeeDiscount extends DiscountBase {

    @Override
    double calculateDiscount(double total) {
        return total *.3;
    }
}
