package ua.nure.andreiko.rentCar.web.filter;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.db.Role;
import ua.nure.andreiko.rentCar.db.UserStatus;
import ua.nure.andreiko.rentCar.util.Constant;
import ua.nure.andreiko.rentCar.util.LoggerUtils;
import ua.nure.andreiko.rentCar.util.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Security filter.
 *
 * @author E.Andreiko
 */
public class CommandAccessFilter implements Filter {

    private Map<Role, List<String>> accessMap = new HashMap<Role, List<String>>();

    private List<String> commons = new ArrayList<String>();

    private List<String> outOfControl = new ArrayList<String>();

    Logger LOGGER = Logger.getLogger(CommandAccessFilter.class);

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        LOGGER.debug(LoggerUtils.FILTER_INIT_START);

        accessMap.put(Role.ADMIN, asList(fConfig.getInitParameter("admin")));
        accessMap.put(Role.CLIENT, asList(fConfig.getInitParameter("client")));
        accessMap.put(Role.MANAGER, asList(fConfig.getInitParameter("manager")));
        commons = asList(fConfig.getInitParameter("common"));
        outOfControl = asList(fConfig.getInitParameter("out-of-control"));

        LOGGER.trace(LoggerUtils.FILTER_ACCESS_MAP + accessMap);
        LOGGER.trace(LoggerUtils.FILTER_COMMON_COMMANDS + commons);
        LOGGER.trace(LoggerUtils.FILTER_OUT_OF_CONTROL_COMMANDS + outOfControl);
        LOGGER.debug(LoggerUtils.FILTER_INIT_END);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (accessAllowed(servletRequest)) {
            LOGGER.debug(LoggerUtils.FILTER_START);
            LOGGER.debug(LoggerUtils.FILTER_END);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            System.out.println("GARBAGE");
            String errorMessage = Constant.ERR_DO_NOT_HAVE_PERMISSION;
            servletRequest.setAttribute("errorMessage", errorMessage);
            LOGGER.trace(LoggerUtils.FILTER_SET_ERR_ATTRIBUTE + errorMessage);
            servletRequest.getRequestDispatcher(Path.PAGE_ERROR_PAGE)
                    .forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        LOGGER.debug(LoggerUtils.FILTER_DESTROY_START);
        LOGGER.debug(LoggerUtils.FILTER_END);
    }

    private boolean accessAllowed(ServletRequest servletRequest) {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String commandName = servletRequest.getParameter("command");
        if (Objects.isNull(commandName) || commandName.isEmpty()) {
            return false;
        }
        HttpSession session = httpRequest.getSession(false);
        if (outOfControl.contains(commandName)) {
            return true;
        }
        if (Objects.isNull(session)) {
            return false;
        }

        Role userRole = (Role) session.getAttribute("userRole");
        UserStatus userStatus = (UserStatus) session.getAttribute("userStatus");
        if (Objects.isNull(userRole) || Objects.isNull(userStatus)) {
            return false;
        }
        if (userStatus.equals(UserStatus.BUNNED)) {
            return false;
        }
        return accessMap.get(userRole).contains(commandName)
                || commons.contains(commandName);
    }

    /**
     * Extracts parameter values from string.
     *
     * @param str parameter values string.
     * @return list of parameter values.
     */
    private List<String> asList(String str) {
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}
