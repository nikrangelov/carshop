package carshop.services;

import carshop.entities.Car;
import carshop.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarService {

    public Car findCarByManufacturer(String manufacturer);

    public Car findCarById(long id);

    public void saveCar(Car car);

    public Page<Car> getCars(Integer pageNumber);

    public void deleteCar(Car car);

    public List<Car> findAll();

}