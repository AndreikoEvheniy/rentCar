package ua.nure.andreiko.rentCar.db.dao;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.db.DBManager;
import ua.nure.andreiko.rentCar.db.entity.Category;
import ua.nure.andreiko.rentCar.exception.DBException;
import ua.nure.andreiko.rentCar.util.DBConstants;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAORepository {

    private final DBManager dbManager;

    private final Logger LOGGER = Logger.getLogger(CategoryDAORepository.class);

    public CategoryDAORepository(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<Category> getCategoryList() throws DBException {
        List<Category> categoryList = new ArrayList<>();
        Connection connection = dbManager.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DBConstants.SQL_GET_ALL_CATEGORY);
            while (resultSet.next()) {
                categoryList.add(extractCategory(resultSet));
            }
            connection.commit();
            LOGGER.info("Categories: " + categoryList);
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.info("Cannot obtain list categories ", e);
            throw new DBException("Unable to connect", e);
        }
        return categoryList;
    }

    private Category extractCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong("id_category"));
        category.setCategory(resultSet.getString("category"));
        return category;
    }
}
