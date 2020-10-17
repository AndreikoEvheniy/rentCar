package ua.nure.andreiko.rentCar.web.bean;

import ua.nure.andreiko.rentCar.db.OrderStatus;

import java.util.Objects;

/**
 * Treatment order bean
 *
 * @author E.Andreiko
 */
public class TreatmentOrderBean {

    private OrderStatus status;

    private String comment;

    private long id;

    private double cost;

    public double getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = (Objects.isNull(cost) || cost.isEmpty()) ? 0 : Double.parseDouble(cost);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus decide) {
        this.status = decide;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId(String id) {
        this.id = (Objects.isNull(id) || id.isEmpty()) ? 0 : Long.parseLong(id);
    }

    public long getId() {
        return id;
    }
}
