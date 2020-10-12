package ua.nure.andreiko.rentCar.db.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Comparable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
