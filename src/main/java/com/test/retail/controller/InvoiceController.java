package com.test.retail.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.retail.model.dto.ItemDto;
import com.test.retail.service.InvoiceService;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final Logger LOG = LoggerFactory.getLogger(InvoiceController.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping(path = "/net",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> calculateNet(@RequestBody MultiValueMap<String, String> body) {
        try {
            int userId = Integer.valueOf(body.get("userId").get(0));

            List<ItemDto> items = body.get("items").stream()
                .map(json -> {
                    try {
                        return mapper.readValue(json, ItemDto.class);
                    } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        throw new IllegalArgumentException("Can not parse Item: " + json);
                    }
                }).collect(Collectors.toList());

            return new ResponseEntity("total after discount: " + invoiceService.calculateNetPayable(userId, items),
                HttpStatus.OK);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
