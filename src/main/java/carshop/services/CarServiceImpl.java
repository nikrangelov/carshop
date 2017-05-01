package carshop.services;

import carshop.entities.Car;
import carshop.entities.Role;
import carshop.entities.User;
import carshop.repositories.CarRepository;
import carshop.repositories.RoleRepository;
import carshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("carService")
public class CarServiceImpl implements CarService{

    private static final int PAGE_SIZE = 2;

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

    @Override
    public Car findCarById(long id){
        return carRepository.findById(id);
    }

    @Override
    public Page<Car> getCars(Integer pageNumber) {
        PageRequest request =
                new PageRequest(pageNumber - 1, PAGE_SIZE);
        return carRepository.findAll(request);
    }

    @Override
    public void deleteCar(Car car){
        carRepository.delete(car);
    }

    @Override
    public List<Car> findAll(){
        return carRepository.findAll();
    }


}