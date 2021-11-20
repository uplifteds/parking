package org.uplifteds.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.uplifteds.app.ddl.SpringJDBC;
import org.uplifteds.app.entity.Car;
import org.uplifteds.app.entity.Customer;
import org.uplifteds.app.interfaces.CarService;
import org.uplifteds.app.interfaces.CustomerService;

@SpringBootApplication(scanBasePackages = {"org.uplifteds.app"})
public class ParkingRESTLauncher {
    public static ApplicationContext context = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");

    static JdbcTemplate jdbcTemplate = ParkingRESTLauncher.context.getBean("jdbcTemplate", JdbcTemplate.class);

    public static void main( String[] args ) {
        Customer.getFieldNameReflection();
        Car.getFieldNameReflection();

        SpringJDBC springJDBCDDLandCrud = new SpringJDBC(jdbcTemplate);

        springJDBCDDLandCrud.doDeleteTableIfExists(SpringJDBC.CAR_TABLE_NAME);
        springJDBCDDLandCrud.doDeleteTableIfExists(SpringJDBC.CUSTOMER_TABLE_NAME);
        springJDBCDDLandCrud.doCreateCustomersTable();
        springJDBCDDLandCrud.doCreateCarsTable();

        // sample initialization, before using REST-approach
        createFirstCustomer();
        createFirstCarForFirstCustomer();

        SpringApplication.run(ParkingRESTLauncher.class, args);
    }

    public static Customer createFirstCustomer() {

        CustomerService customerService = (CustomerService) ParkingRESTLauncher.context.getBean("customerService");
        customerService.createCustomer();
        Customer cust = customerService.getCustomer(1);

        jdbcTemplate.update(
                "INSERT INTO " + SpringJDBC.CUSTOMER_TABLE_NAME + " VALUES (?, ?, ?, ?, ?)",
                cust.getId(), cust.getName(), cust.getLastname(), cust.getEmail(), cust.getPhone());

        return cust;
    }

    public static Car createFirstCarForFirstCustomer() {

        CarService carService = (CarService) ParkingRESTLauncher.context.getBean("carService");
        carService.createCarAndAssignToCustomer();
        Car car = carService.getCar(1);

        jdbcTemplate.update(
                "INSERT INTO " + SpringJDBC.CAR_TABLE_NAME + " VALUES (?, ?, ?, ?, ?)",
                car.getId(), car.getCarnumber(), car.getColor(), car.getModel(), car.getCustomerId());

        return car;
    }

}
