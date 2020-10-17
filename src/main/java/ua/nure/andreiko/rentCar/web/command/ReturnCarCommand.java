package ua.nure.andreiko.rentCar.web.command;

import ua.nure.andreiko.rentCar.db.entity.Order;
import ua.nure.andreiko.rentCar.exception.AppException;
import ua.nure.andreiko.rentCar.util.Constant;
import ua.nure.andreiko.rentCar.util.Path;
import ua.nure.andreiko.rentCar.util.Util;
import ua.nure.andreiko.rentCar.web.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Return car command.
 *
 * @author E.Andreiko
 */
public class ReturnCarCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        String payParameter = request.getParameter("orderId");
        Order order = new Order();
        long orderId = (Objects.isNull(payParameter) || payParameter.isEmpty()) ? 0 : Long.parseLong(payParameter);
        order.setId(orderId);
        Util util = (Util) servletContext.getAttribute(Constant.UTIL);
        if (orderId != 0 && util.isNotReSubmitting(request)) {
            OrderService orderService = (OrderService) servletContext.getAttribute(Constant.ORDER_SERVICE_MANAGER);
            orderService.setReturnedStatus(order);
        }
        return Path.COMMAND_CLIENT_ORDER_LIST;
    }
}
