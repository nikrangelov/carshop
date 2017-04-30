package carshop.repositories;


import carshop.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("carRepository")
public interface CarRepository extends JpaRepository<Car, Integer> {

    public Car findByManufacturer(String role);

}