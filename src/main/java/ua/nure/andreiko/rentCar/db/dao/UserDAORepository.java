package ua.nure.andreiko.rentCar.db.dao;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.db.DBManager;
import ua.nure.andreiko.rentCar.db.dto.UserDTO;
import ua.nure.andreiko.rentCar.db.entity.User;
import ua.nure.andreiko.rentCar.exception.DBException;
import ua.nure.andreiko.rentCar.util.DBConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.andreiko.rentCar.db.DBManager.close;

/**
 * User DAO. Works with user repository.
 *
 * @author E.Andreiko
 */
public class UserDAORepository {

    private final DBManager dbManager;

    private final Logger LOGGER = Logger.getLogger(UserDAORepository.class);

    public UserDAORepository(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    /**
     * Get user from db
     * @param user who need to get
     * @return user
     * @throws DBException
     */
    public User getUser(User user) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User findUser = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstants.SQL_GET_USER_BY_LOGIN, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                findUser = extractUser(resultSet);
            }
            connection.commit();
            LOGGER.info("User: " + user);
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.info("Cannot obtain user ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return findUser;
    }

    /**
     *
     * @return list user DTO
     * @throws DBException
     */
    public List<UserDTO> getUserDTO() throws DBException {
        List<UserDTO> userDTOList = new ArrayList<>();
        Connection connection = dbManager.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DBConstants.SQL_GET_USER_DTO);
            while (resultSet.next()) {
                userDTOList.add(extractUserDTO(resultSet));
            }
            connection.commit();
            LOGGER.info("Users DTO list: " + userDTOList);
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain users DTO list ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, statement, resultSet);
        }
        return userDTOList;
    }

    /**
     * Insert an object of user class.
     *
     * @param user which needs to be insert
     * @return true if user was insert
     * @throws DBException
     */
    public boolean insertUser(User user) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstants.SQL_INSERT_USER);
            int i = 1;
            System.out.println(user);
            preparedStatement.setString(i++, user.getLogin());
            preparedStatement.setString(i++, user.getPassword());
            preparedStatement.setString(i++, user.getFirstName());
            preparedStatement.setString(i++, user.getLastName());
            preparedStatement.setInt(i++, user.getAge());
            preparedStatement.setLong(i, user.getIdRole());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info(i + " " + user.getId());
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain insert user ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return true;
    }

    /**
     * Update user
     *
     * @param user which update
     * @return true if update was completed
     * @throws DBException
     */
    public boolean updateUser(User user) throws DBException {
        Connection connection = dbManager.getConnection();
        ;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstants.SQL_UPDATE_USER);
            int i = 1;
            System.out.println(user.getId() + ": " + user);
            preparedStatement.setString(i++, user.getFirstName());
            preparedStatement.setString(i++, user.getLastName());
            preparedStatement.setInt(i++, user.getAge());
            preparedStatement.setLong(i, user.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info("User with id " + user.getId() + " was update");
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain update user ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return true;
    }

    /**
     * Update status user
     *
     * @param user who need update status
     * @return true if update was completed
     * @throws DBException
     */
    public boolean updateUserStatus(User user) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstants.SQL_UPDATE_USER_STATUS);
            int i = 1;
            preparedStatement.setLong(i++, user.getIdStatus());
            preparedStatement.setLong(i, user.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info("User with id " + user.getId() + " was update status");
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain update user status ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement);
        }
        return true;
    }

    /**
     * Extracts a user entity from the result set
     *
     * @param resultSet from which a user entity will be extracted.
     * @return user entity
     * @throws SQLException
     */
    private static User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId((resultSet.getLong("id_user")));
        user.setLogin(resultSet.getString("login"));
        user.setFirstName(resultSet.getString("firstname"));
        user.setLastName(resultSet.getString("lastname"));
        user.setPassword(resultSet.getString("password"));
        user.setIdRole(resultSet.getLong("id_role"));
        user.setIdStatus(resultSet.getLong("id_status"));
        user.setAge(resultSet.getInt("age"));
        return user;
    }

    /**
     * Extracts a user DTO entity from the result set
     *
     * @param resultSet from which a user DTO entity will be extracted.
     * @return user DTO entity
     * @throws SQLException
     */
    private UserDTO extractUserDTO(ResultSet resultSet) throws SQLException {
        UserDTO user = new UserDTO();
        user.setId((resultSet.getLong("id_user")));
        user.setLogin(resultSet.getString("login"));
        user.setFirstName(resultSet.getString("firstname"));
        user.setLastName(resultSet.getString("lastname"));
        user.setRole(resultSet.getString("role"));
        user.setStatus(resultSet.getString("status"));
        user.setAge(resultSet.getInt("age"));
        return user;
    }
}
