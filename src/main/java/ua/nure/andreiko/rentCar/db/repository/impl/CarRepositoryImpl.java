package ua.nure.andreiko.rentCar.db.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.db.DBManager;
import ua.nure.andreiko.rentCar.db.dao.CarDAORepository;
import ua.nure.andreiko.rentCar.db.dto.CarDTO;
import ua.nure.andreiko.rentCar.db.entity.Car;
import ua.nure.andreiko.rentCar.db.repository.CarRepository;
import ua.nure.andreiko.rentCar.exception.DBException;

import java.util.List;

/**
 * Class car repository which the implements interface car repository
 *
 * @author E.Andreiko
 */
public class CarRepositoryImpl implements CarRepository {

    private final Logger LOGGER = Logger.getLogger(CarRepositoryImpl.class);

    private final DBManager dbManager;

    private final CarDAORepository carDAORepository;

    public CarRepositoryImpl(DBManager dbManager, CarDAORepository carDAORepository) {
        this.dbManager = dbManager;
        this.carDAORepository = carDAORepository;
    }

    /**
     * Create new car
     *
     * @param car object which need create
     */
    @Override
    public void createCar(Car car) {
        dbManager.doTransaction(() -> {
            try {
                LOGGER.info("Create car: " + car);
                return carDAORepository.insertCar(car);
            } catch (DBException e) {
                LOGGER.error("Cannot create new car " + e);
            }
            return false;
        });
    }


    /**
     * Update car
     *
     * @param car object which need update
     */
    @Override
    public void updateCar(Car car) {
        dbManager.doTransaction(() -> {
            try {
                LOGGER.info("Update car: " + car);
                return carDAORepository.updateCar(car);
            } catch (DBException e) {
                LOGGER.error("Cannot update car " + e);
            }
            return false;
        });
    }


    /**
     * Remove car
     *
     * @param car object which need delete
     */
    @Override
    public void deleteCar(Car car) {
        dbManager.doTransaction(() -> {
            try {
                LOGGER.info("Delete car: " + car);
                return carDAORepository.deleteCar(car);
            } catch (DBException e) {
                LOGGER.error("Cannot delete car " + e);
            }
            return false;
        });
    }


    /**
     * Get list car DTO
     *
     * @return list car DTO
     */
    @Override
    public List<CarDTO> getAllCarDTO() {
        return dbManager.doTransaction(() -> {
            try {
                LOGGER.info("List with car DTO was get");
                return carDAORepository.getAllCarDTO();
            } catch (DBException e) {
                LOGGER.error("Cannot get list car " + e);
            }
            return null;
        });
    }

}
