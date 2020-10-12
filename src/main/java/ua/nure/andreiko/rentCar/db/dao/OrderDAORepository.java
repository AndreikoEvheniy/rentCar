package ua.nure.andreiko.rentCar.db.dao;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.db.DBManager;
import ua.nure.andreiko.rentCar.db.dto.OrderDTO;
import ua.nure.andreiko.rentCar.db.entity.Order;
import ua.nure.andreiko.rentCar.db.entity.User;
import ua.nure.andreiko.rentCar.exception.DBException;
import ua.nure.andreiko.rentCar.util.DBConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.andreiko.rentCar.db.DBManager.close;

public class OrderDAORepository {

    private final DBManager dbManager;

    private final Logger LOGGER = Logger.getLogger(OrderDAORepository.class);

    public OrderDAORepository(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean insertOrder(Order order) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstants.SQL_INSERT_ORDER);
            int i = 1;
            preparedStatement.setBoolean(i++, order.isWithDriver());
            preparedStatement.setTimestamp(i++, order.getFromDate());
            preparedStatement.setTimestamp(i++, order.getToDate());
            preparedStatement.setLong(i++, order.getIdCar());
            preparedStatement.setLong(i, order.getIdUser());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info(i + " " + order.getId());
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.info("Cannot obtain insert order ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement);
        }
        return true;
    }

    public List<OrderDTO> getOrdersDTOByStatus(Order order) throws DBException {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstants.SQL_MANAGER_GET_ORDER_DTO);
            preparedStatement.setLong(1, order.getIdStatus());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderDTOList.add(extractOrderDTO(resultSet));
            }
            connection.commit();
            LOGGER.info("Order DTO by status: " + orderDTOList);
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.info("Cannot obtain insert order DTO list by status ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return orderDTOList;
    }

    public List<OrderDTO> getOrderDTOByUser(User user) throws DBException {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(DBConstants.SQL_GET_ORDER_DTO);
            preparedStatement.setLong(1, user.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderDTO orderDTO = extractOrderDTO(resultSet);
                orderDTOList.add(orderDTO);
            }
            connection.commit();
            LOGGER.info("Order DTO by status: " + orderDTOList);
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.info("Cannot obtain insert order DTO list by user ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return orderDTOList;
    }

    public boolean updateOrderStatus(Order order) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            int i = 1;
            preparedStatement = connection.prepareStatement(DBConstants.SQL_UPDATE_ORDER_STATUS);
            preparedStatement.setLong(i++, order.getIdStatus());
            preparedStatement.setLong(i, order.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info("Order with id " + order.getId() + " was update");
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain update order ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement);
        }
        return true;
    }

    public boolean updateOrder(Order order) throws DBException {
        Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            System.out.println("ORdER: " + order);
            preparedStatement = connection.prepareStatement(DBConstants.SQL_UPDATE_ORDER);
            int i = 1;
            preparedStatement.setLong(i++, order.getIdStatus());
            preparedStatement.setString(i++, order.getReasonDeny());
            preparedStatement.setLong(i, order.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info("Order with id " + order.getId() + " was update");
        } catch (SQLException e) {
            dbManager.rollback(connection);
            LOGGER.error("Cannot obtain update car ", e);
            throw new DBException("Unable to connect", e);
        } finally {
            close(connection, preparedStatement);
        }
        return true;
    }

    private OrderDTO extractOrderDTO(ResultSet resultSet) throws SQLException {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(resultSet.getLong("id_order"));
        orderDTO.setFirstName(resultSet.getString("firstname"));
        orderDTO.setLastName(resultSet.getString("lastname"));
        orderDTO.setModel(resultSet.getString("model"));
        orderDTO.setDriver(resultSet.getBoolean("driver"));
        orderDTO.setFromDate(resultSet.getTimestamp("fromdate"));
        orderDTO.setToDate(resultSet.getTimestamp("todate"));
        orderDTO.setStatus(resultSet.getString("status"));
        orderDTO.setReasonDeny(resultSet.getString("reasondeny"));
        orderDTO.setCost(resultSet.getDouble("cost"));
        return orderDTO;
    }
}
