package ua.nure.andreiko.rentCar.db;

import ua.nure.andreiko.rentCar.db.entity.User;

public enum UserStatus {
    UNBUNNED, BUNNED, PENALTY;

    public static UserStatus getStatus(User user) {
        return UserStatus.values()[(int) (user.getIdStatus() - 1)];
    }


    public static UserStatus getStatus(long id) {
        return UserStatus.values()[(int) (id - 1)];
    }

    public long getNumber() {
        return this.ordinal() + 1;
    }
}
