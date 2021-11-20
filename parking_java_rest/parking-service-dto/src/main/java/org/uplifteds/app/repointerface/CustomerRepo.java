package org.uplifteds.app.repointerface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.uplifteds.app.entity.Customer;

import java.sql.ResultSet;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    RowMapper<Customer> ROW_MAPPER = (ResultSet rs, int rowNum) ->
            new Customer(rs.getInt(Customer.idFieldName), rs.getString(Customer.nameFieldName),
                    rs.getString(Customer.lastnameFieldName), rs.getString(Customer.emailFieldName),
                    rs.getString(Customer.phoneFieldName));
}