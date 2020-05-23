package com.test.retail.service;

import com.test.retail.discount.Discount;
import com.test.retail.discount.DiscountStrategy;
import com.test.retail.model.User;
import com.test.retail.model.dto.ItemDto;
import com.test.retail.repository.UserRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountServiceService {

    private static final String GROCERY_ITEM_TYPE = "grocery";
    private static final String ANONYMOUS_USER_TYPE = "anonymous";

    private final UserRepository userRepository;

    @Autowired
    public DiscountServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public double calculateNetPayable(int userId, List<ItemDto> items) {
        Optional<User> user = findUser(userId);

        Discount discount = getDiscuountStrategy(extractUserType(user), extractJoiningDate(user));

        double total = calculateTotal(items);
        double discountByUserType = discount.doDiscount(calculateTotalWithoutGrocery(items));
        double hundredsDiscount = DiscountStrategy.HUNDREDS.getDiscount().doDiscount(total);

        return total - (discountByUserType + hundredsDiscount);
    }

    private Optional<User> findUser(int userId) {
        return userRepository.findById(userId);
    }

    private Date extractJoiningDate(Optional<User> user) {
        Date joinningDate = new Date();

        if (user.isPresent()) {
            joinningDate = user.get().getJoiningDate();
        }

        return joinningDate;
    }

    private String extractUserType(Optional<User> user) {
        String userType = ANONYMOUS_USER_TYPE;

        if (user.isPresent()) {
            userType = user.get().getUserType().getType();
        }

        return userType;
    }

    private Discount getDiscuountStrategy(String userType, Date joinningDate) {
        return DiscountStrategy.getDiscountStrategy(userType, joinningDate);
    }

    private double calculateTotal(List<ItemDto> items) {
        return items.stream()
            .filter(item -> item != null)
            .map(item -> item.getPrice())
            .reduce(0.0, (item, item2) -> item + item2);
    }

    private double calculateTotalWithoutGrocery(List<ItemDto> items) {
        return items.stream()
            .filter(item -> item != null)
            .filter(item -> !item.getType().equalsIgnoreCase(GROCERY_ITEM_TYPE))
            .map(item -> item.getPrice())
            .reduce(0.0, (item, item2) -> item + item2);
    }

}
