package org.uplifteds.app.interfaces;

import org.uplifteds.app.entity.Customer;

import java.util.List;

public interface CustomerService {
    void createCustomer();
    void updateCustomer(int id, Customer ev);
    Customer getCustomer(int id);
    void deleteCustomer(int id);
    List<Customer> getAllCustomers();

}
