package ua.nure.andreiko.rentCar.db.repository;

import ua.nure.andreiko.rentCar.db.entity.Brand;

import java.util.List;

public interface BrandRepository {

    List<Brand> getBrandList();
}
