package ua.nure.andreiko.rentCar.web.command;

import ua.nure.andreiko.rentCar.db.dto.OrderDTO;
import ua.nure.andreiko.rentCar.exception.AppException;
import ua.nure.andreiko.rentCar.util.Constant;
import ua.nure.andreiko.rentCar.util.Path;
import ua.nure.andreiko.rentCar.web.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Check car view application command
 *
 * @author E.Andreiko
 */
public class CheckCarViewCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        OrderService orderService = (OrderService) servletContext.getAttribute(Constant.ORDER_SERVICE_MANAGER);
        List<OrderDTO> orderDTOList = orderService.getReturningOrdersDTO();
        request.setAttribute("orderDTOList", orderDTOList);
        String selectedOption = request.getParameter("decide");
        if (Objects.nonNull(selectedOption) && !selectedOption.isEmpty()) {
            request.setAttribute("selectedOption", selectedOption);
        }
        return Path.PAGE_MANAGER_RETURN_CAR;
    }
}
