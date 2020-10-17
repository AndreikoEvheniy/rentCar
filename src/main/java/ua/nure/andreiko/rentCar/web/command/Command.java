package ua.nure.andreiko.rentCar.web.command;

import ua.nure.andreiko.rentCar.exception.AppException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main interface for the Command pattern implementation.
 *
 * @author E.Andreiko
 */
public abstract class Command {

    protected static ServletContext servletContext;

    public abstract String execute(HttpServletRequest request,
                                   HttpServletResponse response)
            throws IOException, ServletException, AppException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }

    public static void setContext(ServletContext servletContext) {
        Command.servletContext = servletContext;
    }
}
