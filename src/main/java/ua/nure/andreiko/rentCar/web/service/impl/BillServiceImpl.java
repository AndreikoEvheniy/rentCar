package ua.nure.andreiko.rentCar.web.service.impl;

import ua.nure.andreiko.rentCar.db.dto.OrderDTO;
import ua.nure.andreiko.rentCar.db.entity.Bill;
import ua.nure.andreiko.rentCar.db.entity.Order;
import ua.nure.andreiko.rentCar.db.repository.BillRepository;
import ua.nure.andreiko.rentCar.web.bean.AcceptCarBean;
import ua.nure.andreiko.rentCar.web.bean.PayOrderBean;
import ua.nure.andreiko.rentCar.web.bean.TreatmentOrderBean;
import ua.nure.andreiko.rentCar.web.service.BillService;

import java.util.List;

/**
 * Class bill service which the implements interface bill service
 *
 * @author E.Andreiko
 */
public class BillServiceImpl implements BillService {

    private final BillRepository BILL_REPOSITORY;

    public BillServiceImpl(BillRepository BILL_REPOSITORY) {
        this.BILL_REPOSITORY = BILL_REPOSITORY;
    }

    /**
     * Create new bill
     *
     * @param acceptCarBean accept car nean
     */
    @Override
    public void createBill(AcceptCarBean acceptCarBean) {
        BILL_REPOSITORY.createBill(getEntity(acceptCarBean));
    }

    /**
     * Create new bill
     *
     * @param treatmentOrderBean
     */
    @Override
    public void createBill(TreatmentOrderBean treatmentOrderBean) {
        BILL_REPOSITORY.createBill(getEntity(treatmentOrderBean));
    }

    /**
     * Get entity bill
     * @param treatmentOrderBean which need to get bill
     * @return entity bill
     */
    private Bill getEntity(TreatmentOrderBean treatmentOrderBean) {
        Bill bill = new Bill();
        bill.setOrderId(treatmentOrderBean.getId());
        bill.setCost(treatmentOrderBean.getCost());
        bill.setReason(treatmentOrderBean.getComment());
        return bill;
    }

    /**
     * Get entity bill
     * @param acceptCarBean which need to get bill
     * @return entity bill
     */
    private Bill getEntity(AcceptCarBean acceptCarBean) {
        Bill bill = new Bill();
        bill.setOrderId(acceptCarBean.getId());
        bill.setCost(acceptCarBean.getCost());
        bill.setReason(acceptCarBean.getComment());
        return bill;
    }

    /**
     * Get list bill
     *
     * @param orderDTO which need to get bill
     * @return list bill
     */
    @Override
    public List<Bill> getBillsForOneOrder(OrderDTO orderDTO) {
        return BILL_REPOSITORY.getBills(getEntity(orderDTO));
    }

    /**
     * Pay for bill
     *
     * @param payOrderBean which need to pay for bill
     */
    @Override
    public void payForBill(PayOrderBean payOrderBean) {
        BILL_REPOSITORY.updateBill(getEntity(payOrderBean));
    }

    private Bill getEntity(PayOrderBean payOrderBean) {
        Bill bill = new Bill();
        bill.setId(payOrderBean.getIdBill());
        bill.setPaid(true);
        return bill;
    }

    private Order getEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        return order;
    }
}
