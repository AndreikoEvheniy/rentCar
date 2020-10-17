package ua.nure.andreiko.rentCar.web.service;

import ua.nure.andreiko.rentCar.db.dto.UserDTO;
import ua.nure.andreiko.rentCar.db.entity.User;
import ua.nure.andreiko.rentCar.web.bean.AuthBean;
import ua.nure.andreiko.rentCar.web.bean.RegistrationBean;
import ua.nure.andreiko.rentCar.web.bean.SettingBean;
import ua.nure.andreiko.rentCar.web.bean.UpdateStatusBean;

import java.util.List;

/**
 * User interface for connection with repository
 *
 * @author E.Andreiko
 */
public interface UserService {

    void updateUserStatus(UpdateStatusBean updateStatusBean);

    void updateUserInfo(SettingBean settingBean);

    void createClient(RegistrationBean registrationBean);

    void createManager(RegistrationBean registrationBean);

    List<UserDTO> getUserDTO();

    User findUser(AuthBean authBean);

    User findUser(RegistrationBean registrationBean);

    User findUser(User user);
}
