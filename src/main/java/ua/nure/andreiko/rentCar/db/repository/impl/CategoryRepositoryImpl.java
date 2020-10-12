package ua.nure.andreiko.rentCar.db.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.db.DBManager;
import ua.nure.andreiko.rentCar.db.dao.BrandDAORepository;
import ua.nure.andreiko.rentCar.db.dao.CategoryDAORepository;
import ua.nure.andreiko.rentCar.db.entity.Category;
import ua.nure.andreiko.rentCar.db.repository.CategoryRepository;
import ua.nure.andreiko.rentCar.exception.DBException;

import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    private final Logger LOGGER = Logger.getLogger(BrandRepositoryImpl.class);

    private final DBManager dbManager;

    private final CategoryDAORepository categoryDAORepository;

    public CategoryRepositoryImpl(DBManager dbManager, CategoryDAORepository categoryDAORepository) {
        this.dbManager = dbManager;
        this.categoryDAORepository = categoryDAORepository;
    }

    @Override
    public List<Category> getCategoryList() {
        return dbManager.doTransaction(() -> {
            try {
                LOGGER.info("List with category was get");
                return categoryDAORepository.getCategoryList();
            } catch (DBException e) {
                LOGGER.error("Cannot get list category " + e);
            }
            return null;
        });
    }
}
