/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syzegee.customer.service.controller;

import com.syzegee.customer.service.adapter.CustomerDBAdapter;
import com.syzegee.customer.service.datafetcher.CustomerDataFetcher;
import com.syzegee.customer.service.entity.Customer;
import com.syzegee.customer.service.entity.Organization;
import com.syzegee.customer.service.service.CustomerService;
import graphql.schema.DataFetcher;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;

/**
 * @author Sangam
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CustomerService customerService;
    @MockBean
    CustomerDBAdapter customerDBAdapter;


    /**
     * Test of getCustomer method, of class CustomerController.
     */
    @Test
    public void testGetCustomer() throws Exception {
        System.out.println("testGetCustomer");
        String url = "/api/v1/customers";
        Long id = 1L;
        String corelationId="47f73502-1826-4489-9218-c383a4ebee81";
        Organization organization = Organization.builder().organizationId(id).organizationName("Amazon").build();
        Customer customer = Customer.builder().customerId(id).organizationId(organization).businessName("Software").build();
        CustomerDataFetcher customerDataFetcher = new CustomerDataFetcher(customerDBAdapter);
        customerDataFetcher.setCorelationId(corelationId);
        when(customerService.retrieveCustomerById(corelationId)).
                thenReturn(customerDataFetcher);
        when(customerDBAdapter.getCustomerById(id,corelationId))
                .thenReturn(customer);
        String payload = "{\n" +
                "customer(id:\"1\"){\n" +
                "customerId \n" +
                "userId\n" +
                "buisnessName\n" +
                "organizationId{\n" +
                "organizationId\n" +
                "organizationName\n" +
                "}\n" +
                "}\n" +
                "}";
        String expectedResponse="{\"customer\":{\"customerId\":\"1\",\"userId\":null,\"buisnessName\":\"Software\",\"organizationId\":{\"organizationId\":\"1\",\"organizationName\":\"Amazon\"}}}";

        RequestBuilder request = MockMvcRequestBuilders
                .post(url)
                .header("correlationId",corelationId)
                .content(payload);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        System.out.println("response>>  "+response);
        assertEquals(response,expectedResponse);
    }

}
