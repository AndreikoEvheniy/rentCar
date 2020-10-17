package ua.nure.andreiko.rentCar.db.dao;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.db.DBManager;
import ua.nure.andreiko.rentCar.db.dto.CarDTO;
import ua.nure.andreiko.rentCar.db.entity.Car;
import ua.nure.andreiko.rentCar.exception.DBException;
import ua.nure.andreiko.rentCar.util.DBConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.andreiko.rentCar.db.DBManager.close;

/**
 * Car DAO. Works with car repository.
 *
 * @author E.Andreiko
 */
public class CarDAORepository {

    private final DBManager dbManager;

    private final Logger LOGGER = Logger.getLogger(CarDAORepository.class);

    public CarDAORepository(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    /**
     * Insert an object of car class.
     *
     * @param car which needs to be insert
     * @return true if car was insert
     * @throws DBException
     */
    public boolean insertCar(Car car) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstants.SQL_INSERT_CAR);
            int i = 1;
            preparedStatement.setString(i++, car.getModel());
            preparedStatement.setDouble(i++, car.getCost());
            preparedStatement.setLong(i++, car.getIdBrand());
            preparedStatement.setLong(i, car.getIdCategory());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info(i + " " + car.getId());
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain insert car ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement);
        }
        return true;
    }

    /**
     * Update car
     *
     * @param car which update
     * @return true if update was completed
     * @throws DBException
     */
    public boolean updateCar(Car car) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstants.SQL_UPDATE_CAR);
            int i = 1;
            preparedStatement.setString(i++, car.getModel());
            preparedStatement.setDouble(i++, car.getCost());
            preparedStatement.setLong(i++, car.getIdBrand());
            preparedStatement.setLong(i++, car.getIdCategory());
            preparedStatement.setLong(i, car.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info("Car with id " + car.getId() + " was update");
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain update car ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement);
        }
        return true;
    }

    /**
     * Remove car from db
     *
     * @param car which remove
     * @return true if remove was completed
     * @throws DBException
     */
    public boolean deleteCar(Car car) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstants.SQL_DELETE_CAR);
            preparedStatement.setLong(1, car.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info("Car with id " + car.getId() + " was delete");
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain delete car ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement);
        }
        return true;
    }

    /**
     *
     * @return list car DTO
     * @throws DBException
     */
    public List<CarDTO> getAllCarDTO() throws DBException {
        List<CarDTO> carDTOList = new ArrayList<>();
        Connection connection = dbManager.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DBConstants.SQL_GET_ALL_CAR_DTO);
            while (resultSet.next()) {
                carDTOList.add(extractCarDTO(resultSet));
            }
            connection.commit();
            LOGGER.info("Car DTO: " + carDTOList);
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain carDTO ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, statement, resultSet);
        }
        return carDTOList;
    }

    /**
     * Extracts a car DTO entity from the result set
     *
     * @param resultSet from which a car DTO entity will be extracted.
     * @return car DTO entity
     * @throws SQLException
     */
    public CarDTO extractCarDTO(ResultSet resultSet) throws SQLException {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(resultSet.getLong("id_car"));
        carDTO.setBrand(resultSet.getString("brand"));
        carDTO.setModel(resultSet.getString("model"));
        carDTO.setCost(resultSet.getDouble("cost"));
        carDTO.setCategory(resultSet.getString("category"));
        return carDTO;
    }
}
