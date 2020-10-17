package ua.nure.andreiko.rentCar.db.entity;

import java.util.Objects;

/**
 * Car entity.
 *
 * @author E.Andreiko
 */
public class Car extends Entity {

    private String model;

    private long idBrand;

    private double Cost;

    private long idCategory;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(long idBrand) {
        this.idBrand = idBrand;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double cost) {
        Cost = cost;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", idBrand=" + idBrand +
                ", Cost=" + Cost +
                ", idCategory=" + idCategory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return model.equals(car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }

    @Override
    public int compareTo(Object o) {
        Car c = (Car) o;
        return model.compareTo(c.model);
    }
}
