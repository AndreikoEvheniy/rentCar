package ua.nure.andreiko.rentCar.db.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.db.DBManager;
import ua.nure.andreiko.rentCar.db.dao.BrandDAORepository;
import ua.nure.andreiko.rentCar.db.entity.Brand;
import ua.nure.andreiko.rentCar.db.repository.BrandRepository;
import ua.nure.andreiko.rentCar.exception.DBException;

import java.util.List;

/**
 * Class brand repository which the implements interface brand repository
 *
 * @author E.Andreiko
 */
public class BrandRepositoryImpl implements BrandRepository {

    private final Logger LOGGER = Logger.getLogger(BrandRepositoryImpl.class);

    private final DBManager dbManager;

    private final BrandDAORepository brandDAORepository;

    public BrandRepositoryImpl(DBManager dbManager, BrandDAORepository brandDAORepository) {
        this.dbManager = dbManager;
        this.brandDAORepository = brandDAORepository;
    }

    /**
     * @return list brand
     */
    @Override
    public List<Brand> getBrandList() {
        return dbManager.doTransaction(() -> {
            try {
                LOGGER.info("List with bills by order was get");
                return brandDAORepository.getBrandList();
            }catch (DBException e){
                LOGGER.error("Cannot get list brand " + e);
            }
            return null;
        });
    }
}
