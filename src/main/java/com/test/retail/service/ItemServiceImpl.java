package com.test.retail.service;

import com.test.retail.model.Item;
import com.test.retail.repository.ItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;

    @Autowired
    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Item> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }
}
