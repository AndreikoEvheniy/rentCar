package ua.nure.andreiko.rentCar.web.command;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands
 *
 * @author E.Andreiko
 */
public class CommandContainer {

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        commands.put("login", new LogInCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("registrationView", new RegistrationViewCommand());
        commands.put("loginView", new LoginViewCommand());

        commands.put("logout", new LogOutCommand());
        commands.put("viewSettings", new SettingsViewCommand());
        commands.put("settingsChange", new ChangeSettingsCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("changeLocale", new ChangeLocaleCommand());

        commands.put("makeOrder", new MakeOrderCommand());
        commands.put("searchCar", new SearchCarCommand());
        commands.put("orderClientList", new PayOrderViewCommand());
        commands.put("payOrder", new PayOrderCommand());
        commands.put("returnCar", new ReturnCarCommand());

        commands.put("checkCar", new CheckCarViewCommand());
        commands.put("acceptCar", new AcceptCarCommand());
        commands.put("listOrders", new CheckOrderViewCommand());
        commands.put("treatmentOrder", new TreatmentOrderCommand());

        commands.put("adminPanel", new ControlViewCommand());
        commands.put("addCar", new AddCarCommand());
        commands.put("updateCar", new UpdateCarCommand());
        commands.put("deleteCar", new DeleteCarCommand());
        commands.put("addManager", new AddManagerCommand());
        commands.put("updateUserStatus",new UpdateUserStatusCommand());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName Name of the command.
     * @return Command object.
     */
    public static Command getCommand(String commandName) {
        if (commandName == null) {
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }

    private CommandContainer(){
    }
}
