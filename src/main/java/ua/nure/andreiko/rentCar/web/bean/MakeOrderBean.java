package ua.nure.andreiko.rentCar.web.bean;

import ua.nure.andreiko.rentCar.db.entity.User;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Make order bean
 *
 * @author E.Andreiko
 */
public class MakeOrderBean {

    long from;

    long to;

    long idCar;

    long idUser;

    boolean withDriver;

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(User user) {
        this.idUser = (Objects.isNull(user)) ? 0 : user.getId();
    }

    public boolean isWithDriver() {
        return withDriver;
    }

    public void setWithDriver(String withDriver) {
        this.withDriver = "true".equals(withDriver);
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(Timestamp from) {
        if (Objects.nonNull(from)) {
            this.from = from.getTime();
        } else {
            this.from = 0;
        }
    }

    public long getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        if (Objects.nonNull(to)) {
            this.to = to.getTime();
        } else {
            this.to = 0;
        }
    }

    public long getIdCar() {
        return idCar;
    }

    public void setIdCar(String idCar) {
        this.idCar = (Objects.isNull(idCar) || idCar.isEmpty()) ? 0 : Long.parseLong(idCar);
    }
}
