package org.uplifteds.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uplifteds.app.CarServiceImpl;
import org.uplifteds.app.entity.Car;
import org.uplifteds.app.interfaces.CarService;
import org.uplifteds.app.repointerface.CarRepo;

import java.util.List;
import java.util.Optional;

@RestController
public class CarServiceController {
   private final String CAR_API_URL_ROUTE = "/carapi";
   @Autowired
   CarRepo carRepo;

   @Autowired
   private CarService carService;

   @PostMapping(CAR_API_URL_ROUTE+ "/add")
   public void addNewCar(@RequestBody Car newCar) {
      CarServiceImpl.listOfCars.add(newCar);
      carRepo.save(newCar);
      System.out.println("new car was posted via REST: " + newCar.toString());
   }

   @GetMapping(CAR_API_URL_ROUTE + "/{id}")
   public Optional<Car> getCarById(@PathVariable int id) {
      return carRepo.findById(id);
   }

   @GetMapping(CAR_API_URL_ROUTE)
   public List<Car> getAllCars() {
      System.out.println("getCars via REST: " + carService.getAllCarsOfTheCustomer());
      return carRepo.findAll();
   }

   @DeleteMapping(CAR_API_URL_ROUTE+ "/delete/{id}")
   void deleteCar(@PathVariable int id) {
      carService.deleteCarFromCustomer(id);
      carRepo.deleteById(id);
      System.out.println("car with id " + id + " was deleted via REST");
   }

   @PutMapping(CAR_API_URL_ROUTE + "/update/{id}")
   Car updateCar(@RequestBody Car updCar, @PathVariable int id) {
      System.out.println("car was updated via REST (id won't be updated) " + updCar.toString());
      return carRepo.findById(id).map(oldCar -> {
         oldCar.setCarnumber(updCar.getCarnumber());
         oldCar.setColor(updCar.getColor());
         oldCar.setModel(updCar.getModel());
         oldCar.setCustomerId(updCar.getCustomerId());

         carService.updateCar(id, updCar);

         return carRepo.save(oldCar);
      }).orElseGet(() -> {
         updCar.setId(id);
         return carRepo.save(updCar);
      });
   }

}
