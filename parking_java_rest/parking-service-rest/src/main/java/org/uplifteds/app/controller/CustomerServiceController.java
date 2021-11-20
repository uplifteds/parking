package org.uplifteds.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.uplifteds.app.CustomerServiceImpl;
import org.uplifteds.app.entity.Customer;
import org.uplifteds.app.interfaces.CustomerService;
import org.uplifteds.app.repointerface.CustomerRepo;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerServiceController {
   private final String CUSTOMER_API_URL_ROUTE = "/customerapi";
   @Autowired
   CustomerRepo customerRepo;

   @Autowired
   private CustomerService customerService;

   @PostMapping(CUSTOMER_API_URL_ROUTE + "/add")
   public void addNewCustomer(@RequestBody Customer newCustomer) {
      CustomerServiceImpl.listOfCustomers.add(newCustomer);
      customerRepo.save(newCustomer);
      System.out.println("new Customer was posted via REST: " + newCustomer.toString());
   }

   @GetMapping(CUSTOMER_API_URL_ROUTE + "/{id}")
   public Optional<Customer> getCustomerById(@PathVariable int id) {
      return customerRepo.findById(id);
   }

   @GetMapping(CUSTOMER_API_URL_ROUTE)
   public List<Customer> getAllCustomers() {
      System.out.println("getlist via REST: " + customerService.getAllCustomers());
      return customerRepo.findAll();
   }

   @DeleteMapping(CUSTOMER_API_URL_ROUTE + "/delete/{id}")
   void deleteCustomer(@PathVariable int id) {
      customerService.deleteCustomer(id);
      customerRepo.deleteById(id);
      System.out.println("Customer with id " + id + " was deleted via REST");
   }

   @PutMapping(CUSTOMER_API_URL_ROUTE + "/update/{id}")
   Customer updateCustomer(@RequestBody Customer updCustomer, @PathVariable int id) {
      System.out.println("Customer was updated via REST (id won't be updated) " + updCustomer.toString());
      return customerRepo.findById(id).map(oldCustomer -> {
         oldCustomer.setName(updCustomer.getName());
         oldCustomer.setLastname(updCustomer.getLastname());
         oldCustomer.setEmail(updCustomer.getEmail());
         oldCustomer.setPhone(updCustomer.getPhone());

         customerService.updateCustomer(id, updCustomer);

         return customerRepo.save(oldCustomer);
      }).orElseGet(() -> {
         updCustomer.setId(id);
         return customerRepo.save(updCustomer);
      });
   }

   public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/swagger-ui/").setViewName("forward:/swagger-ui/index.html");
   }


}
