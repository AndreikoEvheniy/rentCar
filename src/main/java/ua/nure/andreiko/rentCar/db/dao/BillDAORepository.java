package ua.nure.andreiko.rentCar.db.dao;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.db.DBManager;
import ua.nure.andreiko.rentCar.db.entity.Bill;
import ua.nure.andreiko.rentCar.db.entity.Order;
import ua.nure.andreiko.rentCar.exception.DBException;
import ua.nure.andreiko.rentCar.util.DBConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.andreiko.rentCar.db.DBManager.close;

public class BillDAORepository {

    private final DBManager dbManager;

    private final Logger LOGGER = Logger.getLogger(BillDAORepository.class);

    public BillDAORepository(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean insertBill(Bill bill) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstants.SQL_INSERT_BILL);
            int i = 1;
            preparedStatement.setDouble(i++, bill.getCost());
            preparedStatement.setString(i++, bill.getReason());
            preparedStatement.setLong(i, bill.getOrderId());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info(i + " " + bill.getId());
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.info("Cannot obtain insert bill ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement);
        }
        return true;
    }

    public List<Bill> getBills(Order order) throws DBException {
        List<Bill> billList = new ArrayList<>();
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstants.SQL_GET_BILLS_BY_ORDER);
            preparedStatement.setLong(1, order.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                billList.add(extractBill(resultSet));
            }
            LOGGER.info("Bills: " + billList);
            connection.commit();
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.info("Cannot obtain bills by order ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return billList;
    }

    public boolean updateBill(Bill bill) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstants.SQL_UPDATE_BILL);
            int i = 1;
            preparedStatement.setLong(i++, bill.getId());
            preparedStatement.setBoolean(i++, bill.isPaid());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info("Bill with id " + bill.getId() + " was update");
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.info("Cannot obtain update bill ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement);
        }
        return true;
    }

    private Bill extractBill(ResultSet resultSet) throws SQLException {
        Bill bill = new Bill();
        bill.setId(resultSet.getLong("id_bill"));
        bill.setCost(resultSet.getDouble("cost"));
        bill.setReason(resultSet.getString("reason"));
        bill.setOrderId(resultSet.getLong("id_order"));
        return bill;
    }
}
