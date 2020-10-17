package ua.nure.andreiko.rentCar.db.dto;

import ua.nure.andreiko.rentCar.db.entity.Entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Order Data Transfer Object.
 *
 * @author E.Andreiko
 */
public class OrderDTO extends Entity {

    private boolean driver;

    private java.sql.Date fromDate;

    private java.sql.Date toDate;

    private String firstName;

    private String lastName;

    private String reasonDeny;

    private String model;

    private String status;

    private double cost;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isDriver() {
        return driver;
    }

    public void setDriver(boolean driver) {
        this.driver = driver;
    }

    public java.sql.Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = new java.sql.Date((fromDate.getTime()));
    }

    public java.sql.Date getToDate() {
        return toDate;
    }

    public void setToDate(Timestamp toDate) {
        this.toDate = new Date((toDate.getTime()));
    }

    public String getReasonDeny() {
        return reasonDeny;
    }

    public void setReasonDeny(String reasonDeny) {
        this.reasonDeny = reasonDeny;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(fromDate.toString(), formatter);
        LocalDate endDate = LocalDate.parse(toDate.toString(), formatter);
        Period period = Period.between(startDate, endDate);
        double driverCost = (driver) ? 50.0 : 0.0;
        cost = period.getDays() * (cost + driverCost);
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return driver == orderDTO.driver &&
                fromDate.equals(orderDTO.fromDate) &&
                toDate.equals(orderDTO.toDate) &&
                Objects.equals(reasonDeny, orderDTO.reasonDeny) &&
                model.equals(orderDTO.model) &&
                status.equals(orderDTO.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driver, fromDate, toDate, reasonDeny, model, status);
    }

    @Override
    public int compareTo(Object o) {
        OrderDTO w = (OrderDTO) o;
        return model.compareTo(w.model);
    }
}
