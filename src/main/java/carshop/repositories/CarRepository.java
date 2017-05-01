package carshop.repositories;


import carshop.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("carRepository")
public interface CarRepository extends JpaRepository<Car, Long>  {

    public Car findByManufacturer(String manufacturer);

    public Car findById(long id);

    public List<Car> findAll();
}