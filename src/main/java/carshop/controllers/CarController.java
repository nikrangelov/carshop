package carshop.controllers;

import carshop.entities.Car;
import carshop.entities.User;
import carshop.services.CarService;
import carshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nik on 4/30/17.
 */

@RestController
public class CarController {

    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    @RequestMapping(value = "public/addCar",  method = RequestMethod.POST)
    public ResponseEntity<Car> addCar(@RequestBody Car car){

        // get the current user;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findUserByEmail(name);

        Car newCar = car;
        newCar.setUser(user);
        carService.saveCar(newCar);
        //user.addCar(newCar);

        return new ResponseEntity<Car>(newCar, HttpStatus.OK);
    }

    @RequestMapping(value = "public/getCar/{carId}",  method = RequestMethod.GET)
    public ResponseEntity<Car> getCar(@PathVariable int carId){// get the current user;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findUserByEmail(name);

        Car car = carService.findCarById(carId);



        return new ResponseEntity<Car>(car, HttpStatus.OK);
    }


}
