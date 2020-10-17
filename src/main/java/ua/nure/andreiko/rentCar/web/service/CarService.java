package ua.nure.andreiko.rentCar.web.service;

import ua.nure.andreiko.rentCar.db.dto.CarDTO;
import ua.nure.andreiko.rentCar.db.entity.Brand;
import ua.nure.andreiko.rentCar.db.entity.Category;
import ua.nure.andreiko.rentCar.web.bean.OperationCarBean;

import java.util.List;

/**
 * Car interface for connection with repository
 *
 * @author E.Andreiko
 */
public interface CarService {

    void createCar(OperationCarBean operationCarBean);

    void updateCar(OperationCarBean operationCarBean);

    void deleteCar(OperationCarBean operationCarBean);

    List<CarDTO> getAllCarDTO();

    List<Category> getCategoryList();

    List<Brand> getBrandList();
}
