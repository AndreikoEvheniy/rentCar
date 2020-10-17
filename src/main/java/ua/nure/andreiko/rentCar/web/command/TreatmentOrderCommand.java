package ua.nure.andreiko.rentCar.web.command;

import ua.nure.andreiko.rentCar.db.OrderStatus;
import ua.nure.andreiko.rentCar.exception.AppException;
import ua.nure.andreiko.rentCar.util.Constant;
import ua.nure.andreiko.rentCar.util.Path;
import ua.nure.andreiko.rentCar.util.Util;
import ua.nure.andreiko.rentCar.web.bean.TreatmentOrderBean;
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
 * Treatment order command.
 *
 * @author E.Andreiko
 */
public class TreatmentOrderCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        String statusParameter = request.getParameter("decide");
        String commentParameter = request.getParameter("comment");
        String orderIdParameter = request.getParameter("order");
        String costParameter = request.getParameter("cost");

        OrderStatus status = OrderStatus.getOrder(statusParameter);

        TreatmentOrderBean treatmentOrderBean = new TreatmentOrderBean();
        treatmentOrderBean.setId(orderIdParameter);
        treatmentOrderBean.setCost(costParameter);
        treatmentOrderBean.setStatus(status);
        treatmentOrderBean.setComment(commentParameter);
        Locale locale = (Locale) request.getSession().getAttribute(Constant.LOCALE);

        Validator validator = (Validator) servletContext.getAttribute(Constant.VALIDATOR);
        Util util = (Util) servletContext.getAttribute(Constant.UTIL);
        Map<String, String> errors = validator.validate(treatmentOrderBean,locale);
        request.setAttribute("errors", errors);

        if (errors.isEmpty() && util.isNotReSubmitting(request)) {
            OrderService orderService = (OrderService) servletContext.getAttribute(Constant.ORDER_SERVICE_MANAGER);
            if (status.equals(OrderStatus.ACCEPTED)) {
                treatmentOrderBean.setComment("Rent");
                BillService billService = (BillService) servletContext.getAttribute(Constant.BILL_SERVICE_MANAGER);
                orderService.setAcceptedStatus(treatmentOrderBean);
                billService.createBill(treatmentOrderBean);
            } else {
                orderService.setRejectedStatus(treatmentOrderBean);
            }
        }
        return Path.COMMAND_MANAGER_LIST_ORDERS;
    }
}
