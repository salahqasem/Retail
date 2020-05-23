package com.test.retail.controller;

import com.test.retail.service.InvoiceService;
import com.test.retail.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = {InvoiceController.class})
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceService invoiceService;

    @MockBean
    private ItemService itemService;

    @Test
    public void shouldReturnDiscount() throws Exception {
        when(invoiceService.calculateNetPayable(anyInt(), anyList()))
            .thenReturn(5.0);

        mockMvc.perform(post("/invoice/net")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .param("userId", "1")
            .param("items", "{\"name\":\"orange\",\"price\":80,\"type\":\"grocery\"}"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("total after discount: 5.0")));;
    }

    @Test
    public void shouldReturnInternalServerErrorWhenDataInvalid() throws Exception {
        when(invoiceService.calculateNetPayable(anyInt(), anyList()))
            .thenThrow(new RuntimeException());

        mockMvc.perform(post("/invoice/net")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .param("userid", "sa"))
            .andDo(print())
            .andExpect(status().isInternalServerError());
    }

}
