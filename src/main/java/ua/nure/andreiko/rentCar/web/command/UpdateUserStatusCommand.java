package ua.nure.andreiko.rentCar.web.command;

import ua.nure.andreiko.rentCar.exception.AppException;
import ua.nure.andreiko.rentCar.util.Constant;
import ua.nure.andreiko.rentCar.util.Path;
import ua.nure.andreiko.rentCar.util.Util;
import ua.nure.andreiko.rentCar.web.bean.UpdateStatusBean;
import ua.nure.andreiko.rentCar.web.service.UserService;
import ua.nure.andreiko.rentCar.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * Update user status command.
 *
 * @author E.Andreiko
 */
public class UpdateUserStatusCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        String idParameter = request.getParameter("id_user");
        String statusParameter =request.getParameter("status");

        int idStatus = Integer.parseInt(statusParameter);

        UpdateStatusBean statusBean = new UpdateStatusBean();
        statusBean.setId(idParameter);
        statusBean.setStatus(idStatus);
        Locale locale = (Locale) request.getSession().getAttribute(Constant.LOCALE);

        Validator validator = (Validator) servletContext.getAttribute(Constant.VALIDATOR);
        Map<String, String> errors = validator.validate(statusBean,locale);
        request.setAttribute("errorsStatus", errors);
        Util util = (Util) servletContext.getAttribute(Constant.UTIL);
        if (errors.isEmpty()&&util.isNotReSubmitting(request)) {
            UserService userService = (UserService) servletContext.getAttribute(Constant.USER_SERVICE_MANAGER);
            userService.updateUserStatus(statusBean);
        }
        return Path.COMMAND_ADMIN_PANEL;
    }
}


