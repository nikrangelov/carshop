package carshop.services;


import carshop.entities.Car;
import carshop.entities.User;

public interface UserService {

    public User findUserByEmail(String email);
	public void saveUser(User user);

}