package com.test.retail.discount;

import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

public enum DiscountStrategy {

    AFFILIATE(new AffiliateDiscount()),
    CUSTOMER(new CustomerDiscount()),
    EMPLOYEE(new EmployeeDiscount()),
    NOOP_DISCOUNT(new NoopDiscount()),
    HUNDREDS(new HundredsDiscount());

    private Discount discount;

    DiscountStrategy(Discount discount) {
        this.discount = discount;
    }

    public static Discount getDiscountStrategy(String userType, Date joinningDate) {


        Optional<DiscountStrategy> strategy = Arrays.asList(DiscountStrategy.values())
            .stream()
            .filter(discountStrategy -> discountStrategy.name().equalsIgnoreCase(userType))
            .findAny();

        if (!strategy.isPresent() || (strategy.get().equals(CUSTOMER) && getPeriod(joinningDate).getYears() < 2)) {
            return NOOP_DISCOUNT.discount;
        }

        return strategy.get().discount;
    }

    public Discount getDiscount() {
        return discount;
    }

    private static Period getPeriod(Date joinningDate) {
        return Period.between(joinningDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                , new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}
