package ua.nure.andreiko.rentCar.web.listener;

import ua.nure.andreiko.rentCar.db.DBManager;
import ua.nure.andreiko.rentCar.db.dao.*;
import ua.nure.andreiko.rentCar.db.repository.*;
import ua.nure.andreiko.rentCar.db.repository.impl.*;
import ua.nure.andreiko.rentCar.util.Constant;
import ua.nure.andreiko.rentCar.util.Util;
import ua.nure.andreiko.rentCar.web.command.Command;
import ua.nure.andreiko.rentCar.web.service.BillService;
import ua.nure.andreiko.rentCar.web.service.CarService;
import ua.nure.andreiko.rentCar.web.service.OrderService;
import ua.nure.andreiko.rentCar.web.service.UserService;
import ua.nure.andreiko.rentCar.web.service.impl.BillServiceImpl;
import ua.nure.andreiko.rentCar.web.service.impl.CarServiceImpl;
import ua.nure.andreiko.rentCar.web.service.impl.OrderServiceImpl;
import ua.nure.andreiko.rentCar.web.service.impl.UserServiceImpl;
import ua.nure.andreiko.rentCar.web.validator.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Servlet listener
 *
 * @author E.Andreiko
 */
public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        Command.setContext(servletContext);

        DBManager dbManager = DBManager.getInstance();
        UserService userService = getUserService(dbManager);
        BillService billService = getBillService(dbManager);
        CarService carService = getCarService(dbManager);
        OrderService orderService = getOrderService(dbManager);

        Util util = new Util();
        Validator validator = new Validator(util);


        servletContext.setAttribute(Constant.VALIDATOR, validator);
        servletContext.setAttribute(Constant.UTIL, util);
        servletContext.setAttribute(Constant.USER_SERVICE_MANAGER,userService);
        servletContext.setAttribute(Constant.BILL_SERVICE_MANAGER,billService);
        servletContext.setAttribute(Constant.CAR_SERVICE_MANAGER,carService);
        servletContext.setAttribute(Constant.ORDER_SERVICE_MANAGER, orderService);
    }



    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private UserService getUserService(DBManager dbManager) {
        UserDAORepository userDAORepository = new UserDAORepository(dbManager);
        UserRepository userRepository = new UserRepositoryImpl(userDAORepository, dbManager);
        return new UserServiceImpl(userRepository);
    }

    private CarService getCarService(DBManager dbManager) {
        CarDAORepository carDAORepository = new CarDAORepository(dbManager);
        BrandDAORepository brandDAORepository = new BrandDAORepository(dbManager);
        CategoryDAORepository categoryDAORepository = new CategoryDAORepository(dbManager);

        CarRepository carRepository = new CarRepositoryImpl(dbManager, carDAORepository);
        BrandRepository brandRepository = new BrandRepositoryImpl(dbManager, brandDAORepository);
        CategoryRepository categoryRepository = new CategoryRepositoryImpl(dbManager, categoryDAORepository);
        return new CarServiceImpl(carRepository, brandRepository, categoryRepository);
    }

    private OrderService getOrderService(DBManager dbManager) {
        OrderDAORepository orderDAORepository = new OrderDAORepository(dbManager);
        OrderRepository orderRepository = new OrderRepositoryImpl(orderDAORepository, dbManager);
        return new OrderServiceImpl(orderRepository);
    }

    private BillService getBillService(DBManager dbManager) {
        BillDAORepository billDAORepository = new BillDAORepository(dbManager);
        BillRepository billRepository = new BillRepositoryImpl(dbManager, billDAORepository);
        return new BillServiceImpl(billRepository);
    }
}
