package org.uplifteds.app.interfaces;

import org.uplifteds.app.entity.Car;
import java.util.List;

public interface CarService {
    void createCarAndAssignToCustomer();
    void updateCar(int id, Car car);
    Car getCar(int id);
    void deleteCarFromCustomer(int id);
    List<Car> getAllCarsOfTheCustomer();
}
