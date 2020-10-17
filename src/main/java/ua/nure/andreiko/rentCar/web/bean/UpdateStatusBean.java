package ua.nure.andreiko.rentCar.web.bean;

import ua.nure.andreiko.rentCar.db.UserStatus;

import java.util.Objects;

/**
 * Update status bean
 *
 * @author E.Andreiko
 */
public class UpdateStatusBean {

    private long id;

    private UserStatus userStatus;

    public UserStatus getStatus() {
        return userStatus;
    }

    public void setStatus(long idStatus) {
        this.userStatus = UserStatus.getStatus(idStatus);
    }

    public long getId() {
        return id;
    }

    public void setId(String id) {
        this.id = (Objects.isNull(id) || id.isEmpty()) ? 0 : Long.parseLong(id);
    }
}
