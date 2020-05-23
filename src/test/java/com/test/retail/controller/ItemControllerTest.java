package com.test.retail.controller;

import com.test.retail.model.Item;
import com.test.retail.service.DiscountServiceService;
import com.test.retail.service.ItemService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @MockBean
    private DiscountServiceService discountServiceService;

    @Test
    public void shouldReturnItemsAndSuccess() throws Exception {

        Item item1 = new Item();
        item1.setName("item1");
        Item item2 = new Item();
        item2.setName("item2");

        List<Item> items = new ArrayList();
        items.add(item1);
        items.add(item2);

        when(itemService.getAll()).thenReturn(items);

        mockMvc.perform(get("/item/all"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("item1")));
    }

    @Test
    public void shouldReturnInternalError() throws Exception {
        when(itemService.getAll()).thenThrow(new RuntimeException());
        mockMvc.perform(get("/item/all"))
            .andDo(print())
            .andExpect(status().isInternalServerError());
    }
}
