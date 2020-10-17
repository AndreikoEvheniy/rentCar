package ua.nure.andreiko.rentCar.db.repository.impl;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.db.DBManager;
import ua.nure.andreiko.rentCar.db.dao.BillDAORepository;
import ua.nure.andreiko.rentCar.db.entity.Bill;
import ua.nure.andreiko.rentCar.db.entity.Order;
import ua.nure.andreiko.rentCar.db.repository.BillRepository;
import ua.nure.andreiko.rentCar.exception.DBException;

import java.util.List;

/**
 * Class bill repository which the implements interface bill repository
 *
 * @author E.Andreiko
 */
public class BillRepositoryImpl implements BillRepository {

    private final DBManager dbManager;

    private final Logger LOGGER = Logger.getLogger(BillRepositoryImpl.class);

    private final BillDAORepository billDAORepository;

    public BillRepositoryImpl(DBManager DB_MANAGER, BillDAORepository BILL_DAO_REPOSITORY) {
        this.dbManager = DB_MANAGER;
        this.billDAORepository = BILL_DAO_REPOSITORY;
    }

    /**
     * Create new bill
     *
     * @param bill object which need create
     */
    @Override
    public void createBill(Bill bill) {
        dbManager.doTransaction(() -> {
            try {
                LOGGER.info("Create bill: " + bill);
                return billDAORepository.insertBill(bill);
            } catch (DBException e) {
                LOGGER.error("Cannot create new user " + e);
            }
            return false;
        });
    }

    /**
     * Get list bill by order
     *
     * @param order by which get list bill
     * @return list bill
     */
    @Override
    public List<Bill> getBills(Order order) {
        return dbManager.doTransaction(() -> {
            try {
                LOGGER.info("List with bills by order was get" + order);
                return billDAORepository.getBills(order);
            } catch (DBException e) {
                LOGGER.error("Cannot get list bills " + e);
            }
            return null;
        });
    }

    /**
     * Update bill
     *
     * @param bill object which need update
     */
    @Override
    public void updateBill(Bill bill) {
        dbManager.doTransaction(() -> {
            try {
                LOGGER.info("Bill was update" + bill);
                return billDAORepository.updateBill(bill);
            } catch (DBException e) {
                LOGGER.error("Cannot update bill " + e);
            }
            return false;
        });
    }
}
