package ua.nure.andreiko.rentCar.web.command;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.util.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * No command.
 *
 * @author E.Andreiko
 */
public class NoCommand extends Command{

    private static final Logger LOG = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");
        System.out.println("noCommand");
        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);
        LOG.error("Set the request attribute: errorMessage --> " + errorMessage);

        LOG.debug("Command finished");
        return Path.PAGE_ERROR_PAGE;
    }
}
