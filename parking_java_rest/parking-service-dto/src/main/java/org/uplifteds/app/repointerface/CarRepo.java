package org.uplifteds.app.repointerface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.uplifteds.app.entity.Car;

import java.sql.ResultSet;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {
    RowMapper<Car> ROW_MAPPER = (ResultSet rs, int rowNum) ->
            new Car(rs.getInt(Car.idFieldName), rs.getString(Car.carnumberFieldName),
                    rs.getString(Car.colorFieldName), rs.getString(Car.modelFieldName),
                    rs.getInt(Car.customerIdFieldName));
}