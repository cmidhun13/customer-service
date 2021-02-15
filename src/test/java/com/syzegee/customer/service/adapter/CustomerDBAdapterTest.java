/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syzegee.customer.service.adapter;

import com.syzegee.customer.service.entity.Customer;
import com.syzegee.customer.service.entity.Organization;
import com.syzegee.customer.service.repository.CustomerRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Sangam
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerDBAdapterTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerDBAdapter customerAdapter;


    public CustomerDBAdapterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCustomerById method, of class CustomerAdapter.
     */
    @Test
    public void testGetCustomerById() {
        System.out.println("getCustomerById");
        Long id = 1L;
        Organization organization = Organization.builder().organizationId(id).organizationName("Amazon").build();
        Customer customer = Customer.builder().customerId(id).organizationId(organization).businessName("Software").build();
        when(customerRepository.getRecordById(id)).
                thenReturn(customer);

        Customer result = customerAdapter.getCustomerById(id,"0b39f1c2-efca-43f6-8404-e7ecac077880");
        assertEquals(customer.getBusinessName(), result.getBusinessName());
        assertEquals(customer.getOrganizationId().getOrganizationName(),
                result.getOrganizationId().getOrganizationName());
    }

    /**
     * Test of getAllCustomer method, of class CustomerAdapter.
     */
    @Test
    public void testGetAllCustomer() {
        System.out.println("getAllCustomer");
        List<Customer> expResult = new ArrayList<>();
        Organization organization1 = Organization.builder().organizationId(1l).organizationName("Amazon").build();
        Customer customer1 = Customer.builder().customerId(1L).organizationId(organization1).businessName("SOftare").build();
        Organization organization2 = Organization.builder().organizationId(2l).organizationName("Uber").build();
        Customer customer2 =Customer.builder().customerId(2L).organizationId(organization2).businessName("media").build();
        expResult.add(customer1);
        expResult.add(customer2);
        when(customerRepository.findAll()).
                thenReturn(expResult);
        List<Customer> result = customerAdapter.getAllCustomer();
        assertEquals(expResult, result);
        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.get(0).getOrganizationId().getOrganizationName(),
                result.get(0).getOrganizationId().getOrganizationName());
    }

}
