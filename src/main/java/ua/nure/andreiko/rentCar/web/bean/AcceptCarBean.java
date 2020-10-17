package ua.nure.andreiko.rentCar.web.bean;

import java.util.Objects;

/**
 * Accept car bean
 *
 * @author E.Andreiko
 */
public class AcceptCarBean {

    long id;

    String decide;

    String comment;

    double cost;

    public long getId() {
        return id;
    }

    public void setId(String id) {
        this.id = (Objects.isNull(id) || id.isEmpty()) ? 0 : Long.parseLong(id);
    }

    public String getDecide() {
        return decide;
    }

    public void setDecide(String decide) {
        this.decide = decide;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = (Objects.isNull(cost) || cost.isEmpty()) ? 0 : Double.parseDouble(cost);
    }
}
