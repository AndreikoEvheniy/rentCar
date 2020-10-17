package ua.nure.andreiko.rentCar.web.command;

import ua.nure.andreiko.rentCar.util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Log out command.
 *
 * @author E.Andreiko
 */
public class LogOutCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return Path.PAGE_LOGIN;
    }

}
