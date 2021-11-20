package org.uplifteds.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uplifteds.app.entity.Customer;
import org.uplifteds.app.interfaces.CustomerService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    public static List<Customer> listOfCustomers = new ArrayList<>();
    public static int currentCustomerId;
    public static final int DB_ID_JAVA_COLLECTION_OFFSET = 1;

    public CustomerServiceImpl(Customer customer) {
        this.customer = customer;
    }

    @Autowired
    private Customer customer;

    public void createCustomer() {
        currentCustomerId++;
        if (customer != null) {
            listOfCustomers.add(customer);
        }
        System.out.println("new Customer created");
    }

    @Override
    public List<Customer> getAllCustomers() {
        return listOfCustomers;
    }

    @Override
    public Customer getCustomer(int id) {
        Customer customer1 = listOfCustomers.get(id - DB_ID_JAVA_COLLECTION_OFFSET);
        return customer1;
    }

    @Override
    public void deleteCustomer(int id) {
        Customer customerToRemove = getCustomer(id);
        listOfCustomers.remove(customerToRemove);
        System.out.println("Customer was deleted");
    }

    @Override
    public void updateCustomer(int id, Customer updCustomer) {
        System.out.println("Customer was updated (id won't be updated)");
        Customer oldCustomer = listOfCustomers.get(id - DB_ID_JAVA_COLLECTION_OFFSET);

        oldCustomer.setName(updCustomer.getName());
        oldCustomer.setLastname(updCustomer.getLastname());
        oldCustomer.setEmail(updCustomer.getEmail());
        oldCustomer.setPhone(updCustomer.getPhone());

    }

}
