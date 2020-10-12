package ua.nure.andreiko.rentCar.db.dao;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.db.DBManager;
import ua.nure.andreiko.rentCar.db.entity.Brand;
import ua.nure.andreiko.rentCar.exception.DBException;
import ua.nure.andreiko.rentCar.util.DBConstants;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.andreiko.rentCar.db.DBManager.close;

public class BrandDAORepository {

    private final Logger LOGGER = Logger.getLogger(BrandDAORepository.class);

    private final DBManager dbManager;

    public BrandDAORepository(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<Brand> getBrandList() throws DBException {
        List<Brand> brandList = new ArrayList<>();
        Connection connection = dbManager.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DBConstants.SQL_GET_ALL_BRAND);
            while (resultSet.next()) {
                brandList.add(extractBrand(resultSet));
            }
            connection.commit();
            LOGGER.info("Brands: " + brandList);
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.info("Cannot obtain list brand ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, statement, resultSet);
        }
        return brandList;
    }

    private Brand extractBrand(ResultSet resultSet) throws SQLException {
        Brand brand = new Brand();
        brand.setId(resultSet.getLong("id_brand"));
        brand.setBrand(resultSet.getString("brand"));
        return brand;
    }
}
