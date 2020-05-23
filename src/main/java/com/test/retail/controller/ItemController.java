package com.test.retail.controller;

import com.test.retail.model.Item;
import com.test.retail.service.ItemService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    private static final Logger LOG = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @GetMapping("/all")
    public ResponseEntity<List<Item>> getAllItems() {
        try {
            return new ResponseEntity<>(itemService.getAll(), HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
