package ua.nure.andreiko.rentCar.db.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.db.DBManager;
import ua.nure.andreiko.rentCar.db.dao.UserDAORepository;
import ua.nure.andreiko.rentCar.db.dto.UserDTO;
import ua.nure.andreiko.rentCar.db.entity.User;
import ua.nure.andreiko.rentCar.db.repository.UserRepository;
import ua.nure.andreiko.rentCar.exception.DBException;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class);

    private final UserDAORepository userDAORepository;

    private final DBManager dbManager;

    public UserRepositoryImpl(UserDAORepository userDAORepository, DBManager dbManager) {
        this.userDAORepository = userDAORepository;
        this.dbManager = dbManager;
    }

    @Override
    public User getUser(User user) {
        return dbManager.doTransaction(() -> {
            try {
                LOGGER.info("User was get" + user);
                return userDAORepository.getUser(user);
            } catch (DBException e) {
                LOGGER.error("Cannot get user " + e);
            }
            return null;
        });
    }

    @Override
    public void createUser(User user) {
        dbManager.doTransaction(() -> {
            try {
                LOGGER.info("Create user: " + user);
                return userDAORepository.insertUser(user);
            } catch (DBException e) {
                LOGGER.error("Cannot create new user " + e);
            }
            return false;
        });
    }

    @Override
    public void updateUser(User user) {
        dbManager.doTransaction(() -> {
            try {
                LOGGER.info("Update user: " + user);
                return userDAORepository.updateUser(user);
            } catch (DBException e) {
                LOGGER.error("Cannot update user " + e);
            }
            return false;
        });
    }

    @Override
    public void updateUserStatus(User user) {
        dbManager.doTransaction(() -> {
            try {
                LOGGER.info("Update user status: " + user);
                return userDAORepository.updateUserStatus(user);
            } catch (DBException e) {
                LOGGER.error("Cannot update user status " + e);
            }
            return false;
        });
    }

    @Override
    public List<UserDTO> getUserDTO() {
        return dbManager.doTransaction(() -> {
            try {
                LOGGER.info("List with user DTO was get");
                return userDAORepository.getUserDTO();
            } catch (DBException e) {
                LOGGER.error("Cannot get list user " + e);
            }
            return null;
        });
    }
}
