package ua.nure.andreiko.rentCar.web.service;

import ua.nure.andreiko.rentCar.db.dto.OrderDTO;
import ua.nure.andreiko.rentCar.db.entity.Order;
import ua.nure.andreiko.rentCar.db.entity.User;
import ua.nure.andreiko.rentCar.web.bean.AcceptCarBean;
import ua.nure.andreiko.rentCar.web.bean.MakeOrderBean;
import ua.nure.andreiko.rentCar.web.bean.PayOrderBean;
import ua.nure.andreiko.rentCar.web.bean.TreatmentOrderBean;

import java.util.List;

/**
 * Order interface for connection with repository
 *
 * @author E.Andreiko
 */
public interface OrderService {

    void setAcceptedStatus(TreatmentOrderBean treatmentOrderBean);

    void setRejectedStatus(TreatmentOrderBean treatmentOrderBean);

    void setPaidStatus(PayOrderBean payOrderBean);

    void setReturnedStatus(Order order);

    void setClosedStatus(AcceptCarBean acceptCarBean);

    List<OrderDTO> getOrderDTOByUser(User user);

    List<OrderDTO> getReturningOrdersDTO();

    List<OrderDTO> getConsideringOrdersDTO();

    void createAnOrder(MakeOrderBean makeOrderBean);
}
