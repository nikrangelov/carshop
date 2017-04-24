package carshop.dao;

import carshop.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by nik on 4/24/17.
 */
@PersistenceContext(unitName = "adminEntityManagerFactory")
@Repository
public interface UserDao extends CrudRepository<User, Long> {
    List<User> findByFirstName(String lastName);
}