package com.test.retail.service;

import com.test.retail.model.dto.ItemDto;
import java.util.List;

public interface InvoiceService {
    double calculateNetPayable(int userId, List<ItemDto> items);
}
