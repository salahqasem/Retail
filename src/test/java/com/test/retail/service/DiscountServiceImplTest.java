package com.test.retail.service;

import com.test.retail.model.User;
import com.test.retail.model.UserType;
import com.test.retail.model.dto.ItemDto;
import com.test.retail.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DiscountServiceImplTest {

    private UserRepository userRepository = mock(UserRepository.class);

    private DiscountServiceService discountServiceService = new DiscountServiceImpl(userRepository);


    @Test
    public void shouldReturn30PercentDiscountWithHundredsDiscountForEmployee() {
        User user = createMockUser("salah", "employee", new Date());
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        ItemDto item1 = new ItemDto("orange", "grocery", 70);
        ItemDto item2 = new ItemDto("apple", "grocery", 80);
        ItemDto item3 = new ItemDto("apple", "not-grocery", 50);

        List<ItemDto> items = new ArrayList();

        items.add(item1);
        items.add(item2);
        items.add(item3);

        double total = 200;
        double hundredsDiscount = 10;
        double employeeDiscount = 15;

        double manualAfterDiscount = total - (hundredsDiscount + employeeDiscount);
        double afterDiscount = discountServiceService.calculateNetPayable(user.getId(), items);

        assertThat(afterDiscount, is(manualAfterDiscount));
    }

    @Test
    public void shouldReturn10PercentDiscountWithHundredsDiscountForAffiliate() {
        User user = createMockUser("salah", "affiliate", new Date());
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        ItemDto item1 = new ItemDto("orange", "grocery", 70);
        ItemDto item2 = new ItemDto("apple", "grocery", 80);
        ItemDto item3 = new ItemDto("apple", "not-grocery", 50);

        List<ItemDto> items = new ArrayList();

        items.add(item1);
        items.add(item2);
        items.add(item3);

        double total = 200;
        double hundredsDiscount = 10;
        double affiliateDiscount = 5;

        double manualAfterDiscount = total - (hundredsDiscount + affiliateDiscount);
        double afterDiscount = discountServiceService.calculateNetPayable(user.getId(), items);

        assertThat(afterDiscount, is(manualAfterDiscount));
    }

    @Test
    public void shouldReturn5PercentDiscountWithHundredsDiscountForOldCustomer() {
        User user = createMockUser("salah", "customer", new GregorianCalendar(2015, 11, 11).getTime());
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        ItemDto item1 = new ItemDto("orange", "grocery", 70);
        ItemDto item2 = new ItemDto("apple", "grocery", 80);
        ItemDto item3 = new ItemDto("apple", "not-grocery", 50);

        List<ItemDto> items = new ArrayList();

        items.add(item1);
        items.add(item2);
        items.add(item3);

        double total = 200;
        double hundredsDiscount = 10;
        double affiliateDiscount = 2.5;

        double manualAfterDiscount = total - (hundredsDiscount + affiliateDiscount);
        double afterDiscount = discountServiceService.calculateNetPayable(user.getId(), items);

        assertThat(afterDiscount, is(manualAfterDiscount));
    }

    @Test
    public void shouldReturnHundredsDiscountOnlyForNewCustomer() {
        User user = createMockUser("salah", "customer", new Date());
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        ItemDto item1 = new ItemDto("orange", "grocery", 70);
        ItemDto item2 = new ItemDto("apple", "grocery", 80);
        ItemDto item3 = new ItemDto("apple", "not-grocery", 50);

        List<ItemDto> items = new ArrayList();

        items.add(item1);
        items.add(item2);
        items.add(item3);

        double total = 200;
        double hundredsDiscount = 10;

        double manualAfterDiscount = total - hundredsDiscount;
        double afterDiscount = discountServiceService.calculateNetPayable(user.getId(), items);

        assertThat(afterDiscount, is(manualAfterDiscount));
    }

    @Test
    public void shouldReturnHundredsDiscountOnlyForAnonymousUser() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        ItemDto item1 = new ItemDto("orange", "grocery", 70);
        ItemDto item2 = new ItemDto("apple", "grocery", 80);
        ItemDto item3 = new ItemDto("apple", "not-grocery", 50);

        List<ItemDto> items = new ArrayList();

        items.add(item1);
        items.add(item2);
        items.add(item3);

        double total = 200;
        double hundredsDiscount = 10;

        double manualAfterDiscount = total - hundredsDiscount;
        double afterDiscount = discountServiceService.calculateNetPayable(1, items);

        assertThat(afterDiscount, is(manualAfterDiscount));
    }

    private User createMockUser(String name, String type, Date date) {

        UserType userType = new UserType();
        userType.setType(type);

        User user = new User();
        user.setId(1);
        user.setName(name);
        user.setJoiningDate(date);
        user.setUserType(userType);

        return user;
    }

}
