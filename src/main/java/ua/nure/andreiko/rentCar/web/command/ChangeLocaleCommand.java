package ua.nure.andreiko.rentCar.web.command;

import ua.nure.andreiko.rentCar.db.Role;
import ua.nure.andreiko.rentCar.exception.AppException;
import ua.nure.andreiko.rentCar.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.nure.andreiko.rentCar.db.Role.ADMIN;
import static ua.nure.andreiko.rentCar.db.Role.MANAGER;

/**
 * Change locale application command
 *
 * @author E.Andreiko
 */
public class ChangeLocaleCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        Role role = (Role) request.getSession().getAttribute("userRole");
        String forward;
        if (ADMIN.equals(role)) {
            forward = Path.COMMAND_ADMIN_PANEL;
        } else if (MANAGER.equals(role)) {
            forward = Path.COMMAND_MANAGER_LIST_ORDERS;
        } else {
            forward = Path.COMMAND_CLIENT_CAR_LIST;
        }
        return forward;
    }
}