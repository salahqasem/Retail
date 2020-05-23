package com.test.retail.service;

import com.test.retail.model.Item;
import com.test.retail.repository.ItemRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemServiceImplTest {

    private ItemRepository itemRepository = mock(ItemRepository.class);
    private ItemService itemService = new ItemServiceImpl(itemRepository);

    @Test
    public void shouldReturnData() {

        Item item = new Item();
        item.setName("item");
        Item item1 = new Item();
        item1.setName("item2");

        List<Item> items = new ArrayList();
        items.add(item);
        items.add(item1);

        when(itemRepository.findAll()).thenReturn(items);

        assertThat(itemService.getAll(), is(items));
    }
}
