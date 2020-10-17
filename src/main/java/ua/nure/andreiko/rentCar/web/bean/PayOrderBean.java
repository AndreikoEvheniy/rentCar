package ua.nure.andreiko.rentCar.web.bean;

import java.util.Objects;

/**
 * Pay order bean
 *
 * @author E.Andreiko
 */
public class PayOrderBean {

    long idOrder;

    long bill;

    String status;

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = (Objects.isNull(idOrder) || idOrder.isEmpty()) ? 0 : Long.parseLong(idOrder);
    }

    public long getIdBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = (Objects.isNull(bill)||bill.isEmpty())?0:Long.parseLong(bill);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
