package ua.nure.andreiko.rentCar.db.repository;

import ua.nure.andreiko.rentCar.db.entity.Category;

import java.util.List;

/**
 * Interface category repository
 *
 * @author E.Andreiko
 */
public interface CategoryRepository {

    List<Category> getCategoryList();

}
