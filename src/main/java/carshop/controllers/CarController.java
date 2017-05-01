package carshop.controllers;

import carshop.entities.Car;
import carshop.entities.CarPage;
import carshop.entities.User;
import carshop.services.CarService;
import carshop.services.UserService;
import com.sun.deploy.resources.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

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





}
