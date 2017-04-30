package carshop.services;

import carshop.entities.Car;
import carshop.entities.Role;
import carshop.entities.User;
import carshop.repositories.CarRepository;
import carshop.repositories.RoleRepository;
import carshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("carService")
public class CarServiceImpl implements CarService{

    @Autowired
    private CarRepository carRepository;


    @Override
    public Car findCarByManufacturer(String manufacturer){
        return carRepository.findByManufacturer(manufacturer);
    }

    @Override
    public void saveCar(Car car){
        carRepository.save(car);
    }

}