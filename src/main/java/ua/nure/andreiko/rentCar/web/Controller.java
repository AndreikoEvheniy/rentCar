package ua.nure.andreiko.rentCar.web;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.db.UserStatus;
import ua.nure.andreiko.rentCar.db.entity.User;
import ua.nure.andreiko.rentCar.exception.AppException;
import ua.nure.andreiko.rentCar.util.Constant;
import ua.nure.andreiko.rentCar.util.LoggerUtils;
import ua.nure.andreiko.rentCar.util.Path;
import ua.nure.andreiko.rentCar.web.command.Command;
import ua.nure.andreiko.rentCar.web.command.CommandContainer;
import ua.nure.andreiko.rentCar.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Main servlet controller.
 *
 * @author E.Andreiko
 */
public class Controller extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Main method of this controller.
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOGGER.debug(LoggerUtils.COMMAND_START);

        Command command = getCommand(request);
        LOGGER.trace("Obtained command --> " + command);

        String forward = Path.PAGE_ERROR_PAGE;
        try {
            forward = command.execute(request, response);
        } catch (AppException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
        }
        LOGGER.trace(LoggerUtils.COMMAND_FORWARD + forward);

        String language = request.getParameter("language");
        request.setAttribute("selectedLanguage", language);
        LOGGER.debug(LoggerUtils.COMMAND_GO_FORWARD + forward);
        request.getRequestDispatcher(forward).forward(request, response);
    }

    /**
     * Get command from request
     * @param request get from form
     * @return command
     */
    private Command getCommand(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        LOGGER.trace(LoggerUtils.COMMAND_COMMAND_PARAMETER + commandName);
        Command command = CommandContainer.getCommand(commandName);
        UserService userService = (UserService) getServletContext().getAttribute(Constant.USER_SERVICE_MANAGER);
        User user = (User) request.getSession().getAttribute(Constant.USER);
        if (Objects.nonNull(user)) {
            user = userService.findUser(user);
            request.getSession().setAttribute(Constant.USER, user);
            if (UserStatus.BUNNED.getNumber() == user.getIdStatus()) {
                command = CommandContainer.getCommand("logout");
            }
        }
        return command;
    }
}
