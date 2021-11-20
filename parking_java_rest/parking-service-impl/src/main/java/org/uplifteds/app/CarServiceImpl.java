package org.uplifteds.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uplifteds.app.entity.Car;
import org.uplifteds.app.interfaces.CarService;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    public static List<Car> listOfCars = new ArrayList<>();
    public static int currentCarId;

    @Autowired
    private Car car;

    public CarServiceImpl(Car car) {
        this.car = car;
    }

    @Override
    public void createCarAndAssignToCustomer() {
        currentCarId++;
        if (car != null) {
            listOfCars.add(car);
        }
        System.out.println("new Car created");
    }

    @Override
    public List<Car> getAllCarsOfTheCustomer() {
        return listOfCars;
    }

    @Override
    public Car getCar(int id) {
        Car car = listOfCars.get(id - CustomerServiceImpl.DB_ID_JAVA_COLLECTION_OFFSET);
        return car;
    }

    @Override
    public void deleteCarFromCustomer(int id) {
        Car carToRemove = getCar(id);
        listOfCars.remove(carToRemove);
        System.out.println("Car was deleted");
    }

    @Override
    public void updateCar(int id, Car updCar) {
        System.out.println("car was updated (id won't be updated)");
        Car oldCar = listOfCars.get(id - CustomerServiceImpl.DB_ID_JAVA_COLLECTION_OFFSET);

        oldCar.setCarnumber(updCar.getCarnumber());
        oldCar.setColor(updCar.getColor());
        oldCar.setModel(updCar.getModel());

    }


}
