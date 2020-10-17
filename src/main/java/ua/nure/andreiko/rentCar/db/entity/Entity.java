package ua.nure.andreiko.rentCar.db.entity;

import java.io.Serializable;

/**
 * Root of all entities which have identifier field.
 *
 * @author E.Andreiko
 */
public abstract class Entity implements Serializable, Comparable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
