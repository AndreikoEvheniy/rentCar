package ua.nure.andreiko.rentCar.db.entity;

import java.util.Objects;

/**
 * Brand entity.
 *
 * @author E.Andreiko
 */
public class Brand extends Entity {

    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brand='" + brand + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand1 = (Brand) o;
        return Objects.equals(brand, brand1.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand);
    }

    @Override
    public int compareTo(Object o) {
        Brand b = (Brand) o;
        return brand.compareTo(b.brand);
    }
}
