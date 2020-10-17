package ua.nure.andreiko.rentCar.db.repository;

import ua.nure.andreiko.rentCar.db.dto.OrderDTO;
import ua.nure.andreiko.rentCar.db.entity.Order;
import ua.nure.andreiko.rentCar.db.entity.User;

import java.util.List;

/**
 * Interface order repository
 *
 * @author E.Andreiko
 */
public interface OrderRepository {

    void updateOrder(Order order) ;

    void updateOrderStatus(Order order);

    List<OrderDTO> getOrderDTOByUser(User user);

    List<OrderDTO> getOrdersDTOByStatus(Order order);

    void createAnOrder(Order order);
}
