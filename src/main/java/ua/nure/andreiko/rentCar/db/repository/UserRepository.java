package ua.nure.andreiko.rentCar.db.repository;

import ua.nure.andreiko.rentCar.db.dto.UserDTO;
import ua.nure.andreiko.rentCar.db.entity.User;

import java.util.List;

/**
 * Interface user repository
 *
 * @author E.Andreiko
 */
public interface UserRepository {

    User getUser(User user);

    void createUser(User user);

    void updateUser(User user);

    void updateUserStatus(User user);

    List<UserDTO> getUserDTO();

}
