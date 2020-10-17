package ua.nure.andreiko.rentCar.db.dto;

import ua.nure.andreiko.rentCar.db.entity.Entity;

import java.util.Objects;

/**
 * Car Data Transfer Object.
 *
 * @author E.Andreiko
 */
public class CarDTO extends Entity {

    private String model;

    private String brand;

    private double cost;

    private String category;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", cost=" + cost +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return Objects.equals(model, carDTO.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }

    @Override
    public int compareTo(Object o) {
        CarDTO car = (CarDTO) o;
        return model.compareTo(car.model);
    }
}
