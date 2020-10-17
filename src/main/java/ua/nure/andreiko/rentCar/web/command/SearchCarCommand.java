package ua.nure.andreiko.rentCar.web.command;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.db.dto.CarDTO;
import ua.nure.andreiko.rentCar.db.entity.Brand;
import ua.nure.andreiko.rentCar.db.entity.Category;
import ua.nure.andreiko.rentCar.exception.AppException;
import ua.nure.andreiko.rentCar.util.Constant;
import ua.nure.andreiko.rentCar.util.Path;
import ua.nure.andreiko.rentCar.web.service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Search car command.
 *
 * @author E.Andreiko
 */
public class SearchCarCommand extends Command {

    private static final Logger LOG = Logger.getLogger(SearchCarCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        CarService carService = (CarService) servletContext.getAttribute(Constant.CAR_SERVICE_MANAGER);
        List<CarDTO> carDTOList = carService.getAllCarDTO();
        LOG.trace("Found in DB: carList --> " + carDTOList);

        List<Category> categoryList = carService.getCategoryList();
        List<Brand> brandList = carService.getBrandList();

        String brandParameter = request.getParameter("brand");
        String categoryParameter = request.getParameter("category");
        String filterParameter = request.getParameter("filter");

        List<CarDTO> resultCarDTO = makeResultList(carDTOList, brandParameter, categoryParameter);

        sortCar(filterParameter, resultCarDTO);

        LocalDate currentDate = LocalDate.now();

        String minDate = currentDate.plusDays(1).toString();

        request.setAttribute("minDate", minDate);

        request.setAttribute("brandList", brandList);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("carDTOList", resultCarDTO);

        request.setAttribute("selectedBrand", brandParameter);
        request.setAttribute("selectedCategory", categoryParameter);
        request.setAttribute("selectedFilter", filterParameter);

        LOG.trace("Set the request attribute: carList --> " + carDTOList);
        LOG.debug("Command finished");
        return Path.PAGE_CLIENT_CAR_LIST;
    }

    private void sortCar(String filterParameter, List<CarDTO> resultCarDTO) {
        if ("cost".equals(filterParameter)) {
            resultCarDTO.sort((o1, o2) -> (int) (o1.getCost() - o2.getCost()));
        } else if ("name".equals(filterParameter)) {
            resultCarDTO.sort(CarDTO::compareTo);
        }
    }


    private List<CarDTO> makeResultList(List<CarDTO> carDTOList, String brand, String category) {
        List<CarDTO> list = new ArrayList<>();
        if (isNull(brand, category)) {
            return carDTOList;
        } else if (!brand.equals("all") && !category.equals("all")) {
            for (CarDTO carDTO : carDTOList) {
                if (carDTO.getBrand().equals(brand) && carDTO.getCategory().equals(category)) {
                    list.add(carDTO);
                }
            }
        } else {
            for (CarDTO carDTO : carDTOList) {
                if (carDTO.getBrand().equals(brand) || carDTO.getCategory().equals(category)) {
                    list.add(carDTO);
                }
            }
        }
        return list;
    }

    private boolean isNull(String brand, String category) {
        return (Objects.isNull(brand) && Objects.isNull(category))
                || (Objects.equals(brand, "all") && Objects.equals(category, "all"));
    }
}

