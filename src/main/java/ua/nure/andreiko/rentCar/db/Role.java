package ua.nure.andreiko.rentCar.db;

import ua.nure.andreiko.rentCar.db.entity.User;

public enum Role {

    ADMIN, MANAGER, CLIENT;

    public static Role getRole(User user) {
        return Role.values()[(int) (user.getIdRole() - 1)];
    }


    public int getNumber() {
        return this.ordinal() + 1;
    }
}
