package org.uplifteds.app.ddl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.uplifteds.app.entity.Car;
import org.uplifteds.app.entity.Customer;

public class SpringJDBC {
    private final JdbcTemplate jdbcTemplate;
    public final static String CUSTOMER_TABLE_NAME = "customers";
    public final static String CAR_TABLE_NAME = "cars";

    @Autowired
    public SpringJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void doCreateCustomersTable() {
        System.out.println("Creating new Table ...");
        jdbcTemplate.execute("CREATE TABLE " + CUSTOMER_TABLE_NAME +
                "(" + Customer.idFieldName  + " INTEGER not NULL, " +
                Customer.nameFieldName     + " VARCHAR(50), " +
                Customer.lastnameFieldName     + " VARCHAR(50), " +
                Customer.emailFieldName   + " VARCHAR(50), " +
                Customer.phoneFieldName + " VARCHAR(50), " +
                " PRIMARY KEY (" + Customer.idFieldName + "))");
    }

    public void doCreateCarsTable() {
        System.out.println("Creating new Table ...");
        jdbcTemplate.execute("CREATE TABLE " + CAR_TABLE_NAME +
                "(" + Car.idFieldName  + " INTEGER not NULL, " +
                Car.carnumberFieldName     + " VARCHAR(50), " +
                Car.colorFieldName     + " VARCHAR(50), " +
                Car.modelFieldName   + " VARCHAR(50), " +
                Car.customerIdFieldName + " INTEGER, " +
                " PRIMARY KEY (" + Car.idFieldName + ")," +

                " FOREIGN KEY (" + Car.customerIdFieldName + ") REFERENCES " +
                CUSTOMER_TABLE_NAME + "(" + Customer.idFieldName + "))");
    }

    public void doDeleteTableIfExists(String table) {
        jdbcTemplate.execute("DROP TABLE IF EXISTS " + table);
        System.out.println("Existing Table was deleted");
    }

}
