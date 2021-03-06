package ua.nure.andreiko.rentCar.db.repository;

import ua.nure.andreiko.rentCar.db.dto.CarDTO;
import ua.nure.andreiko.rentCar.db.entity.Car;

import java.util.List;

/**
 * Interface car repository
 *
 * @author E.Andreiko
 */
public interface CarRepository {

    void createCar(Car car);

    void updateCar(Car car);

    void deleteCar(Car car);

    List<CarDTO>getAllCarDTO();
}
