package ua.nure.andreiko.rentCar.db.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.db.DBManager;
import ua.nure.andreiko.rentCar.db.dao.CarDAORepository;
import ua.nure.andreiko.rentCar.db.dto.CarDTO;
import ua.nure.andreiko.rentCar.db.entity.Car;
import ua.nure.andreiko.rentCar.db.repository.CarRepository;
import ua.nure.andreiko.rentCar.exception.DBException;

import java.util.List;

public class CarRepositoryImpl implements CarRepository {

    private final Logger LOGGER = Logger.getLogger(CarRepositoryImpl.class);

    private final DBManager dbManager;

    private final CarDAORepository carDAORepository;

    public CarRepositoryImpl(DBManager dbManager, CarDAORepository carDAORepository) {
        this.dbManager = dbManager;
        this.carDAORepository = carDAORepository;
    }

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

    @Override
    public List<CarDTO> getAllCarDto() {
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
