package ua.nure.andreiko.rentCar.web.command;

import ua.nure.andreiko.rentCar.db.dto.CarDTO;
import ua.nure.andreiko.rentCar.db.dto.UserDTO;
import ua.nure.andreiko.rentCar.db.entity.Brand;
import ua.nure.andreiko.rentCar.db.entity.Category;
import ua.nure.andreiko.rentCar.exception.AppException;
import ua.nure.andreiko.rentCar.util.Constant;
import ua.nure.andreiko.rentCar.util.Path;
import ua.nure.andreiko.rentCar.web.service.CarService;
import ua.nure.andreiko.rentCar.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Control view application command
 *
 * @author E.Andreiko
 */
public class ControlViewCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        UserService userService = (UserService) servletContext.getAttribute(Constant.USER_SERVICE_MANAGER);
        CarService carService = (CarService) servletContext.getAttribute(Constant.CAR_SERVICE_MANAGER);

        List<UserDTO> userDTOList = userService.getUserDTO();
        List<CarDTO> carDTOList = carService.getAllCarDTO();
        List<Brand> brandList = carService.getBrandList();
        List<Category> categoryList = carService.getCategoryList();
        request.setAttribute("userDTOList", userDTOList);
        request.setAttribute("carDTOList", carDTOList);
        request.setAttribute("brandList", brandList);
        request.setAttribute("categoryList", categoryList);
        userDTOList.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        carDTOList.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        return Path.PAGE_ADMIN_CONTROL;
    }
}