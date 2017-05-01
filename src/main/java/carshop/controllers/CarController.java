package carshop.controllers;

import carshop.entities.Car;
import carshop.entities.CarPage;
import carshop.entities.Filter;
import carshop.entities.User;
import carshop.services.CarService;
import carshop.services.UserService;
import com.sun.deploy.resources.Deployment;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by nik on 4/30/17.
 */

@RestController
public class CarController {

    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    @RequestMapping(value = "private/addCar",  method = RequestMethod.POST)
    public ResponseEntity<Car> addCar(WebRequest request, Model model){

        // get the current user;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findUserByEmail(name);

        Car car = new Car();
        car.setManufacturer(request.getParameter("manufacturer"));
        car.setModel(request.getParameter("model"));
        car.setPrice(Integer.parseInt(request.getParameter("price")));
        car.setMilage(Integer.parseInt(request.getParameter("milage")));
        car.setManufactureYear(Integer.parseInt(request.getParameter("manufactureYear")));



        car.setUser(user);
        carService.saveCar(car);
        //user.addCar(newCar);

        return new ResponseEntity<Car>(car, HttpStatus.OK);
    }

    @RequestMapping(value = "public/getCar/{carId}",  method = RequestMethod.GET)
    public ResponseEntity<Car> getCar(@PathVariable int carId){// get the current user;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findUserByEmail(name);

        Car car = carService.findCarById(carId);



        return new ResponseEntity<Car>(car, HttpStatus.OK);
    }

    @RequestMapping(value = "public/updateCar/{carId}", method = RequestMethod.POST)
    public ResponseEntity<Car> updateCar(@RequestBody Car car, @PathVariable long carId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findUserByEmail(name);

        Car newCar = car;
        newCar.setId(carId);
        newCar.setUser(user);

        carService.saveCar(newCar);
        return new ResponseEntity<Car>(newCar, HttpStatus.OK);
    }

    @RequestMapping(value = "public/deleteCar/{carId}", method = RequestMethod.GET)
    public ResponseEntity<Car> deleteCar(@PathVariable long carId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findUserByEmail(name);

        Car car = carService.findCarById(carId);
        if(car.getUser().getEmail().equals(name)){
            carService.deleteCar(car);
        }
        return new ResponseEntity<Car>(car, HttpStatus.OK);
    }

    @RequestMapping(value = "public/pages/{pageNumber}", method = RequestMethod.GET)
    public ResponseEntity<CarPage> getCarPage(@PathVariable Integer pageNumber, Model model) {
        Page<Car> page = carService.getCars(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        CarPage carPage = new CarPage();
        carPage.setPage(page);
        carPage.setBegin(begin);
        carPage.setEnd(end);
        carPage.setCurrent(current);

        return new ResponseEntity<CarPage>(carPage, HttpStatus.OK);
    }

    @RequestMapping(value = "public/filterCars", method = RequestMethod.POST)
    public ResponseEntity<List<Car>> filterCars(@RequestBody Filter filter){
        List<Car> filteredCars = new ArrayList<Car>();
        List<Car> allCars = carService.findAll();

        for(Car car: allCars){
            if(
                    (car.getManufacturer().equals(filter.getManufacturer())) &&
                            (car.getPrice() >= filter.getMinPrice()) &&
                            (car.getPrice() <= filter.getMaxPrice()) &&
                            (car.getMilage() >= filter.getMinMilage()) &&
                            (car.getMilage() <= filter.getMaxMilage()) &&
                            (car.getManufactureYear() >= filter.getMinManufactureYear()) &&
                            (car.getManufactureYear() <= filter.getMaxManufactureYear())
                    ){
                filteredCars.add(car);
            }
        }

        // todo: implement filters
        return new ResponseEntity<List<Car>>(filteredCars, HttpStatus.OK);
    }


    @RequestMapping(value = "public/getFavouriteCars", method = RequestMethod.GET)
    public ResponseEntity<List<Car>> filterCars(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findUserByEmail(name);

        List<Car> favCars = new ArrayList<Car>();

        for(int id: user.getFavouriteCars()){
            Car car = carService.findCarById(id);
            favCars.add(car);
        }


        return new ResponseEntity<List<Car>>(favCars, HttpStatus.OK);
    }

    @RequestMapping(value = "public/addFavouriteCar/{carId}", method = RequestMethod.GET)
    public ResponseEntity<Void> addFavouriteCar(@PathVariable long carId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findUserByEmail(name);

        Car car = carService.findCarById(carId);

        if(car != null){
            user.getFavouriteCars().add((int) carId);
            userService.saveUser(user);
        }


        return new ResponseEntity<Void>(HttpStatus.OK);
    }



    @RequestMapping(value = "public/deleteFavouriteCar/{carId}", method = RequestMethod.GET)
    public ResponseEntity<Void> deleteFavouriteCar(@PathVariable long carId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findUserByEmail(name);

        if(user.getFavouriteCars().contains(carId)){
            user.getFavouriteCars().remove(carId);
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
