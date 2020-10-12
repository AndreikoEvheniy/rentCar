package ua.nure.andreiko.rentCar.db.repository;

import ua.nure.andreiko.rentCar.db.dto.OrderDTO;
import ua.nure.andreiko.rentCar.db.entity.Order;
import ua.nure.andreiko.rentCar.db.entity.User;

import java.util.List;

public interface OrderRepository {

    void createAnOrder(Order order);

    void updateOrder(Order order);

    void updateOrderStatus(Order order);

    List<OrderDTO>getListOrderDTOByUser(User user);

    List<OrderDTO>getListOrderDTOByStatus(Order order);
}
