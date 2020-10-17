package ua.nure.andreiko.rentCar.web.command;

import ua.nure.andreiko.rentCar.exception.AppException;
import ua.nure.andreiko.rentCar.util.Constant;
import ua.nure.andreiko.rentCar.util.Path;
import ua.nure.andreiko.rentCar.util.Util;
import ua.nure.andreiko.rentCar.web.bean.RegistrationBean;
import ua.nure.andreiko.rentCar.web.service.UserService;
import ua.nure.andreiko.rentCar.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * Add manager application command
 *
 * @author E.Andreiko
 */
public class AddManagerCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        String loginParameter = request.getParameter("login");
        String passwordParameter = request.getParameter("password");
        String firstNameParameter = request.getParameter("firstname");
        String lastNameParameter = request.getParameter("lastname");
        String ageParameter = request.getParameter("age");

        UserService userService = (UserService) servletContext.getAttribute(Constant.USER_SERVICE_MANAGER);
        Validator validator = (Validator) servletContext.getAttribute(Constant.VALIDATOR);
        Util util = (Util) servletContext.getAttribute(Constant.UTIL);

        RegistrationBean registrationBean = new RegistrationBean();
        registrationBean.setLogin(loginParameter);
        registrationBean.setPassword(passwordParameter);
        registrationBean.setConfirm(passwordParameter);
        registrationBean.setFirstName(firstNameParameter);
        registrationBean.setLastName(lastNameParameter);
        registrationBean.setAge(ageParameter);
        registrationBean.setUser(userService.findUser(registrationBean));
        Locale locale = (Locale) request.getSession().getAttribute(Constant.LOCALE);

        Map<String, String> errors = validator.validate(registrationBean, locale);
        request.setAttribute("errorsManager", errors);

        if (errors.isEmpty() && util.isNotReSubmitting(request)) {
            userService.createManager(registrationBean);
        }
        return Path.COMMAND_ADMIN_PANEL;
    }
}
