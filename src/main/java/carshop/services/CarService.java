package carshop.services;

import carshop.entities.Car;
import carshop.entities.User;

public interface CarService {

    public Car findCarByManufacturer(String manufacturer);

    public Car findCarById(long id);

    public void saveCar(Car car);

}