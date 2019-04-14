package reporting;

import city.domain.City;
import city.service.CityService;
import country.service.CountryService;
import order.domain.Order;
import order.service.OrderService;
import user.domain.BaseUser;
import user.service.UserService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eliza on 18.03.19.
 */
public class UserOrdersReportGenerator implements ReportGenerator {

    private static final String USER_SEPARATOR = "\n";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final UserService userService;
    private final OrderService orderService;
    private final CountryService countryService;
    private final CityService cityService;

    public UserOrdersReportGenerator(UserService userService, OrderService orderService,
                          CountryService countryService, CityService cityService) {
        this.userService = userService;
        this.orderService = orderService;
        this.countryService = countryService;
        this.cityService = cityService;
    }

    @Override
    public File createReport() throws Exception {

        File tempFile = File.createTempFile(System.currentTimeMillis() + "_user_orders_report", "_io.txt");
        //File file = new File("report.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            List<String> users = getUsers();

            for (String reportLine : users) {
                writer.write(reportLine);
                writer.newLine();
            }
            writer.flush();
        }
        System.out.println("Finished generating report.");
        return tempFile;
    }

    private List<String> getOrdersForUser(BaseUser user){
        List<String> result = new ArrayList<>();

        if (user.getOrders() != null) {
            StringBuilder line;
            for (Order order : user.getOrders()) {
                line = new StringBuilder("Destination: ");
                if (order.getCities() != null) {
                    for (City city : order.getCities()) {
                        line.append(city.getName() + " (" + city.getCountry().getName() + ") ");
                    }
                }
                line.append("Price: " + order.getPrice());
                result.add(line.toString());
            }
        }
        return result;
    }

    private List<String> getUsers(){
        List<String> result = new ArrayList<>();

        if (userService.findAll() != null) {
            StringBuilder line;
            for (BaseUser user : userService.findAll()) {
                line = new StringBuilder("");
                line.append(user.getFirstName() + " " + user.getLastName() + " (" + user.getID() + ")");

                result.add(line.toString());
                result.addAll(getOrdersForUser(user));
                result.add(USER_SEPARATOR);
            }
        }
        return result;
    }
}
