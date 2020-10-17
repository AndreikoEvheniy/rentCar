package ua.nure.andreiko.rentCar.db.repository;

import ua.nure.andreiko.rentCar.db.entity.Bill;
import ua.nure.andreiko.rentCar.db.entity.Order;

import java.util.List;

/**
 * Interface bill repository
 *
 * @author E.Andreiko
 */
public interface BillRepository {

    void createBill(Bill bill);

    List<Bill> getBills(Order order);

    void updateBill(Bill bill);
}
