package ua.nure.andreiko.rentCar.web.bean;

import ua.nure.andreiko.rentCar.db.entity.User;

/**
 * Registration bean
 *
 * @author E.Andreiko
 */
public class RegistrationBean {

    private String login;

    private String password;

    private String confirm;

    private String firstName;

    private String lastName;

    private int age;

    private User user;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(String ageStr) {
        this.age = (ageStr.isEmpty()) ? 0 : Integer.parseInt(ageStr);
    }


    public Object getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
