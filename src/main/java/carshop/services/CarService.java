package carshop.services;

import carshop.entities.Car;
import carshop.entities.User;

public interface CarService {

    public Car findCarByManufacturer(String manufacturer);
    public void saveCar(Car car);

}