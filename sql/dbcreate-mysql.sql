SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS rent DEFAULT CHARACTER SET utf8;
USE rent;

DROP TABLE IF EXISTS user_statuses;

CREATE TABLE IF NOT EXISTS user_statuses
(
    id_status INT         NOT NULL AUTO_INCREMENT,
    status    VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_status)
)
    ENGINE = InnoDB;


DROP TABLE IF EXISTS roles;

CREATE TABLE IF NOT EXISTS roles
(
    id_role INT         NOT NULL AUTO_INCREMENT,
    role    VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_role)
)
    ENGINE = InnoDB;

DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    id_user   INT         NOT NULL AUTO_INCREMENT,
    login     VARCHAR(45) NOT NULL,
    password  VARCHAR(45) NOT NULL,
    firstname VARCHAR(45) NOT NULL,
    lastname  VARCHAR(45) NOT NULL,
    age       INT         NOT NULL,
    id_status INT         NOT NULL DEFAULT 1,
    id_role   INT         NOT NULL DEFAULT 3,
    PRIMARY KEY (id_user),
    UNIQUE INDEX login_UNIQUE (login ASC),
    INDEX fk_users_user_statuses_idx (id_status ASC),
    INDEX fk_users_roles1_idx (id_role ASC),
    CONSTRAINT fk_users_user_statuses
        FOREIGN KEY (id_status)
            REFERENCES user_statuses (id_status)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT fk_users_roles1
        FOREIGN KEY (id_role)
            REFERENCES roles (id_role)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
)
    ENGINE = InnoDB;

DROP TABLE IF EXISTS brands;

CREATE TABLE IF NOT EXISTS brands
(
    id_brand INT         NOT NULL AUTO_INCREMENT,
    brand    VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_brand)
)
    ENGINE = InnoDB;

DROP TABLE IF EXISTS categories;

CREATE TABLE IF NOT EXISTS categories
(
    id_category INT         NOT NULL AUTO_INCREMENT,
    category    VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_category)
)
    ENGINE = InnoDB;

DROP TABLE IF EXISTS car_statuses;

CREATE TABLE IF NOT EXISTS car_statuses
(
    id_status INT         NOT NULL AUTO_INCREMENT,
    status    VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_status)
)
    ENGINE = InnoDB;

DROP TABLE IF EXISTS cars;

CREATE TABLE IF NOT EXISTS cars
(
    id_car      INT         NOT NULL AUTO_INCREMENT,
    model       VARCHAR(45) NOT NULL,
    cost        DOUBLE      NOT NULL,
    id_brand    INT         NULL,
    id_category INT         NULL,
    id_status   INT         NOT NULL DEFAULT 1,
    PRIMARY KEY (id_car),
    INDEX fk_cars_brands1_idx (id_brand ASC),
    INDEX fk_cars_categories1_idx (id_category ASC),
    INDEX fk_cars_car_statuses1_idx (id_status ASC),
    CONSTRAINT fk_cars_brands1
        FOREIGN KEY (id_brand)
            REFERENCES brands (id_brand)
            ON DELETE SET NULL
            ON UPDATE SET NULL,
    CONSTRAINT fk_cars_categories1
        FOREIGN KEY (id_category)
            REFERENCES categories (id_category)
            ON DELETE SET NULL
            ON UPDATE SET NULL,
    CONSTRAINT fk_cars_car_statuses1
        FOREIGN KEY (id_status)
            REFERENCES car_statuses (id_status)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
)
    ENGINE = InnoDB;

DROP TABLE IF EXISTS order_statuses;

CREATE TABLE IF NOT EXISTS order_statuses
(
    id_status INT         NOT NULL AUTO_INCREMENT,
    status    VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_status)
)
    ENGINE = InnoDB;


DROP TABLE IF EXISTS orders;

CREATE TABLE IF NOT EXISTS orders
(
    id_order   INT          NOT NULL AUTO_INCREMENT,
    driver     TINYINT      NOT NULL,
    fromDate   TIMESTAMP    NOT NULL,
    toDate     TIMESTAMP    NOT NULL,
    reasonDeny VARCHAR(100) NULL,
    id_car     INT          NULL,
    id_status  INT          NULL DEFAULT 1,
    id_user    INT          NULL,
    PRIMARY KEY (id_order),
    INDEX fk_orders_cars1_idx (id_car ASC),
    INDEX fk_orders_order_statuses1_idx (id_status ASC),
    INDEX fk_orders_users1_idx (id_user ASC),
    CONSTRAINT fk_orders_cars1
        FOREIGN KEY (id_car)
            REFERENCES cars (id_car)
            ON DELETE SET NULL
            ON UPDATE SET NULL,
    CONSTRAINT fk_orders_order_statuses1
        FOREIGN KEY (id_status)
            REFERENCES order_statuses (id_status)
            ON DELETE SET NULL
            ON UPDATE SET NULL,
    CONSTRAINT fk_orders_users1
        FOREIGN KEY (id_user)
            REFERENCES users (id_user)
            ON DELETE SET NULL
            ON UPDATE SET NULL
)
    ENGINE = InnoDB;

DROP TABLE IF EXISTS bills;

CREATE TABLE IF NOT EXISTS bills
(
    id_bill  INT         NOT NULL AUTO_INCREMENT,
    cost     DOUBLE      NOT NULL,
    reason   VARCHAR(45) NOT NULL,
    paid     TINYINT     NOT NULL DEFAULT '0',
    id_order INT         NOT NULL,
    PRIMARY KEY (id_bill),
    INDEX fk_bills_orders1_idx (id_order ASC),
    CONSTRAINT fk_bills_orders1
        FOREIGN KEY (id_order)
            REFERENCES orders (id_order)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;

INSERT INTO user_statuses(status) value ('unbanned');
INSERT INTO user_statuses(status) value ('banned');
INSERT INTO user_statuses(status) value ('penalty');

INSERT INTO roles(role) value ('admin');
INSERT INTO roles(role) value ('manager');
INSERT INTO roles(role) value ('client');

INSERT INTO users(id_role, id_status, login, password, firstname, lastname, age)
values ('1', '1', 'admin', 'adminpass', 'adminName', 'adminLastName', '18');
INSERT INTO users(id_role, id_status, login, password, firstname, lastname, age)
values ('2', '1', 'manager', 'managerpass', 'managerName', 'managerLastName', '18');
INSERT INTO users(id_role, id_status, login, password, firstname, lastname, age)
values ('3', '1', 'client', 'clientpass', 'clientName', 'clientLastName', '18');

INSERT INTO brands(brand) value ('BMW');
INSERT INTO brands(brand) value ('Audi');
INSERT INTO brands(brand) value ('Mercedes-Benz');

INSERT INTO categories(category) value ('Business');
INSERT INTO categories(category) value ('Standart');
INSERT INTO categories(category) value ('Economy');

INSERT INTO car_statuses(status) value ('available');
INSERT INTO car_statuses(status) value ('booked');

INSERT INTO order_statuses(status) value ('considering');
INSERT INTO order_statuses(status) value ('accepted');
INSERT INTO order_statuses(status) value ('rejected');
INSERT INTO order_statuses(status)
values ('paid');
INSERT INTO order_statuses(status)
values ('returning');
INSERT INTO order_statuses(status)
values ('closed');

INSERT INTO cars(id_brand, id_category, model, cost, id_status) value ('1', '1', 'M5', '150.23', '1');
INSERT INTO cars(id_brand, id_category, model, cost, id_status) value ('1', '2', 'X5', '130.53', '1');
INSERT INTO cars(id_brand, id_category, model, cost, id_status) value ('1', '3', '318', '110.28', '1');

INSERT INTO cars(id_brand, id_category, model, cost, id_status) value ('2', '1', 'A8', '160.21', '1');
INSERT INTO cars(id_brand, id_category, model, cost, id_status) value ('2', '2', 'A5', '140.10', '1');
INSERT INTO cars(id_brand, id_category, model, cost, id_status) value ('2', '3', 'A3', '120.23', '1');

INSERT INTO cars(id_brand, id_category, model, cost, id_status) value ('3', '1', 'AMG GT 63 S', '180.98', '1');
INSERT INTO cars(id_brand, id_category, model, cost, id_status) value ('3', '2', 'E350', '130.50', '1');
INSERT INTO cars(id_brand, id_category, model, cost, id_status) value ('3', '3', 'E220', '100.00', '1');
