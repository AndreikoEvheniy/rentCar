package ua.nure.andreiko.rentCar.web.validator;

import ua.nure.andreiko.rentCar.db.OrderStatus;
import ua.nure.andreiko.rentCar.db.entity.User;
import ua.nure.andreiko.rentCar.util.Util;
import ua.nure.andreiko.rentCar.web.bean.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * For form validation
 *
 * @author E.Andreiko
 */
public class Validator {

    private final Util util;

    private final String PREFIX = "valid_";

    public Validator(Util util) {
        this.util = util;
    }

    public Map<String, String> validate(SettingBean settingBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        String postPrefix = "settings";
        if (Objects.isNull(settingBean.getFirstName()) || settingBean.getFirstName().isEmpty()) {
            errors.put("firstName", util.getTranslate(locale, PREFIX + postPrefix, "firstName"));
        }
        if (Objects.isNull(settingBean.getLastName()) || settingBean.getLastName().isEmpty()) {
            errors.put("lastName", util.getTranslate(locale, PREFIX + postPrefix, "lastName"));
        }
        if (settingBean.getAge() == 0) {
            errors.put("age", util.getTranslate(locale, PREFIX + postPrefix, "age"));
        } else {
            if (settingBean.getAge() < 18) {
                errors.put("age", util.getTranslate(locale, PREFIX + postPrefix, "young"));
            }
        }
        return errors;
    }

    public Map<String, String> validate(RegistrationBean registrationBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        String postPrefix = "admin";
        if (Objects.isNull(registrationBean.getLogin()) || registrationBean.getLogin().isEmpty()) {
            errors.put("login", util.getTranslate(locale, PREFIX + postPrefix, "login"));
        } else {
            if (Objects.nonNull(registrationBean.getUser())) {
                errors.put("login", util.getTranslate(locale, PREFIX + postPrefix, "exist"));
            }
        }
        if (Objects.isNull(registrationBean.getPassword()) || registrationBean.getPassword().isEmpty()) {
            errors.put("password", util.getTranslate(locale, PREFIX + postPrefix, "password"));
        } else {
            if (Objects.isNull(registrationBean.getConfirm()) || registrationBean.getConfirm().isEmpty()) {
                errors.put("confirm", "Fill confirm field");
            } else {
                if (!registrationBean.getPassword().equals(registrationBean.getConfirm())) {
                    errors.put("confirm", util.getTranslate(locale, PREFIX + postPrefix, "mismatch"));
                }
            }
        }
        if (Objects.isNull(registrationBean.getFirstName()) || registrationBean.getFirstName().isEmpty()) {
            errors.put("firstName", util.getTranslate(locale, PREFIX + postPrefix, "firstName"));
        }
        if (Objects.isNull(registrationBean.getLastName()) || registrationBean.getLastName().isEmpty()) {
            errors.put("lastName", util.getTranslate(locale, PREFIX + postPrefix, "lastName"));
        }
        if (registrationBean.getAge() == 0) {
            errors.put("age", util.getTranslate(locale, PREFIX + postPrefix, "age"));
        } else {
            if (registrationBean.getAge() < 18) {
                errors.put("age", util.getTranslate(locale, PREFIX + postPrefix, "young"));
            }
        }
        return errors;
    }

    public Map<String, String> validate(AuthBean authBean) {
        Map<String, String> errors = new HashMap<>();
        User user;
        if (Objects.isNull(authBean.getLogin()) || authBean.getLogin().isEmpty()) {
            errors.put("login", "Fill login field");
        } else {
            if (Objects.nonNull(authBean.getUser())) {
                if (!authBean.getPassword().equals(authBean.getUser().getPassword())) {
                    errors.put("password", "The password is incorrect");
                }
            } else {
                errors.put("login", "User does not exist");
            }
        }
        if (Objects.isNull(authBean.getPassword()) || authBean.getPassword().isEmpty()) {
            errors.put("password", "Fill password field");
        }
        return errors;
    }

    public Map<String, String> validate(OperationCarBean operationCarBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        String postPrefix = "admin";
        if (operationCarBean.getId() < 1) {
            errors.put("id", util.getTranslate(locale, PREFIX + postPrefix, "id"));
        }
        if (Objects.isNull(operationCarBean.getModel()) || operationCarBean.getModel().isEmpty()) {
            errors.put("model", util.getTranslate(locale, PREFIX + postPrefix, "model"));
        }
        if (operationCarBean.getCost() < 1) {
            errors.put("cost", util.getTranslate(locale, PREFIX + postPrefix, "cost"));
        }
        return errors;
    }

    public Map<String, String> validate(UpdateStatusBean statusBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        String postPrefix = "admin";
        if (statusBean.getId() < 1) {
            errors.put("id", util.getTranslate(locale, PREFIX + postPrefix, "id"));
        }
        return errors;
    }

    public Map<String, String> validate(MakeOrderBean makeOrderBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        String postPrefix = "order";
        if (makeOrderBean.getIdCar() == 0) {
            errors.put("car", util.getTranslate(locale, PREFIX + postPrefix, "car"));
        }
        if (makeOrderBean.getTo() == 0 || makeOrderBean.getFrom() == 0) {
            errors.put("date", util.getTranslate(locale, PREFIX + postPrefix, "empty"));
        }
        if (makeOrderBean.getTo() <= makeOrderBean.getFrom()) {
            errors.put("date", util.getTranslate(locale, PREFIX + postPrefix, "incorrect"));
        }
        return errors;
    }

    public Map<String, String> validate(PayOrderBean payOrderBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        String postPrefix = "pay";
        if (payOrderBean.getIdOrder() == 0 || payOrderBean.getIdBill() == 0) {
            errors.put("bill", util.getTranslate(locale, PREFIX + postPrefix, "bill"));
        }
        if (Objects.isNull(payOrderBean.getStatus()) || payOrderBean.getStatus().isEmpty()) {
            errors.put("bill", util.getTranslate(locale, PREFIX + postPrefix, "bill"));
        }
        return errors;
    }

    public Map<String, String> validate(TreatmentOrderBean treatmentOrderBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        String postPrefix = "treatment";
        if (OrderStatus.REJECTED.equals(treatmentOrderBean.getStatus()) &&
                (Objects.isNull(treatmentOrderBean.getComment()) || treatmentOrderBean.getComment().isEmpty())) {
            errors.put("comment", util.getTranslate(locale, PREFIX + postPrefix, "comment"));
        }
        return errors;
    }

    public Map<String, String> validate(AcceptCarBean acceptCarBean, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        String postPrefix = "accept";
        if ("penalty".equals(acceptCarBean.getDecide())) {
            if (Objects.isNull(acceptCarBean.getComment()) || acceptCarBean.getComment().isEmpty()) {
                errors.put("comment", util.getTranslate(locale, PREFIX + postPrefix, "comment"));
            }
            if (acceptCarBean.getCost() == 0) {
                errors.put("cost", util.getTranslate(locale, PREFIX + postPrefix, "cost"));
            }
        }
        return errors;
    }
}
