package ua.nure.andreiko.rentCar.db.entity;

import java.util.Objects;

public class Bill extends Entity{

    private double cost;

    private String reason;

    private long orderId;

    private boolean isPaid;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "cost=" + cost +
                ", reason='" + reason + '\'' +
                ", orderId=" + orderId +
                ", isPaid=" + isPaid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Double.compare(bill.cost, cost) == 0 &&
                orderId == bill.orderId &&
                reason.equals(bill.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost, reason, orderId);
    }

    @Override
    public int compareTo(Object o) {
        Bill bill = (Bill) o;
        return getId().compareTo(bill.getId());
    }
}
