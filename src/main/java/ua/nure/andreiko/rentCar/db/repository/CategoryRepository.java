package ua.nure.andreiko.rentCar.db.repository;

import ua.nure.andreiko.rentCar.db.entity.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> getCategoryList();

}
