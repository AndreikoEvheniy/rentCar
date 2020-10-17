package ua.nure.andreiko.rentCar.web.command;

import ua.nure.andreiko.rentCar.exception.AppException;
import ua.nure.andreiko.rentCar.util.Constant;
import ua.nure.andreiko.rentCar.util.Path;
import ua.nure.andreiko.rentCar.util.Util;
import ua.nure.andreiko.rentCar.web.bean.PayOrderBean;
import ua.nure.andreiko.rentCar.web.service.BillService;
import ua.nure.andreiko.rentCar.web.service.OrderService;
import ua.nure.andreiko.rentCar.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * Pay order command.
 *
 * @author E.Andreiko
 */
public class PayOrderCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String payParameter = request.getParameter("pay");
        String billParameter = request.getParameter("bill");
        String statusParameter = request.getParameter("status");

        Validator validator = (Validator) servletContext.getAttribute("validator");
        Util util = (Util) servletContext.getAttribute("util");

        PayOrderBean payOrderBean = new PayOrderBean();
        payOrderBean.setIdOrder(payParameter);
        payOrderBean.setBill(billParameter);
        payOrderBean.setStatus(statusParameter);
        Locale locale = (Locale) request.getSession().getAttribute(Constant.LOCALE);

        Map<String, String> errors = validator.validate(payOrderBean, locale);

        if (errors.isEmpty() && util.isNotReSubmitting(request)) {
            BillService billService = (BillService) servletContext.getAttribute(Constant.BILL_SERVICE_MANAGER);
            billService.payForBill(payOrderBean);
            if (!"closed".equals(statusParameter)) {
                OrderService orderService = (OrderService) servletContext.getAttribute(Constant.ORDER_SERVICE_MANAGER);
                orderService.setPaidStatus(payOrderBean);
            }
        }
        return Path.COMMAND_CLIENT_ORDER_LIST;
    }
}
