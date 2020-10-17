package ua.nure.andreiko.rentCar.web.service;

import ua.nure.andreiko.rentCar.db.dto.OrderDTO;
import ua.nure.andreiko.rentCar.db.entity.Bill;
import ua.nure.andreiko.rentCar.web.bean.AcceptCarBean;
import ua.nure.andreiko.rentCar.web.bean.PayOrderBean;
import ua.nure.andreiko.rentCar.web.bean.TreatmentOrderBean;

import java.util.List;

/**
 * Bill interface for connection with repository
 *
 * @author E.Andreiko
 */
public interface BillService {

    void createBill(AcceptCarBean acceptCarBean);

    void createBill(TreatmentOrderBean treatmentOrderBean);

    List<Bill> getBillsForOneOrder(OrderDTO order);

    void payForBill(PayOrderBean payOrderBean);
}
