package ua.nure.andreiko.rentCar.web.command;

import ua.nure.andreiko.rentCar.db.entity.User;
import ua.nure.andreiko.rentCar.exception.AppException;
import ua.nure.andreiko.rentCar.util.Constant;
import ua.nure.andreiko.rentCar.util.Path;
import ua.nure.andreiko.rentCar.util.Util;
import ua.nure.andreiko.rentCar.web.bean.MakeOrderBean;
import ua.nure.andreiko.rentCar.web.service.OrderService;
import ua.nure.andreiko.rentCar.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.Map;

/**
 * Make order command.
 *
 * @author E.Andreiko
 */
public class MakeOrderCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        String driverParameter = request.getParameter("driver");
        String fromDateParameter = request.getParameter("fromDate");
        String toDateParameter = request.getParameter("toDate");
        String chooseCarParameter = request.getParameter("chooseCar");

        Util util = (Util) servletContext.getAttribute(Constant.UTIL);
        Validator validator = (Validator) servletContext.getAttribute(Constant.VALIDATOR);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constant.USER);

        Timestamp fromDate = util.convertStringToTimestamp(fromDateParameter);
        Timestamp toDate = util.convertStringToTimestamp(toDateParameter);

        MakeOrderBean makeOrderBean = new MakeOrderBean();
        makeOrderBean.setIdUser(user);
        makeOrderBean.setIdCar(chooseCarParameter);
        makeOrderBean.setWithDriver(driverParameter);
        makeOrderBean.setFrom(fromDate);
        makeOrderBean.setTo(toDate);
        Locale locale = (Locale) request.getSession().getAttribute(Constant.LOCALE);

        Map<String, String> errors = validator.validate(makeOrderBean,locale);
        request.setAttribute("errors", errors);

        if (util.isNotReSubmitting(request) && errors.isEmpty()) {
            OrderService orderService = (OrderService) servletContext.getAttribute(Constant.ORDER_SERVICE_MANAGER);
            orderService.createAnOrder(makeOrderBean);
        }
        return Path.COMMAND_CLIENT_CAR_LIST;
    }

}
