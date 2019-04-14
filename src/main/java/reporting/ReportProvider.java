package reporting;

import city.service.CityService;
import country.service.CountryService;
import order.service.OrderService;
import user.service.UserService;

import java.io.File;

/**
 * Created by eliza on 18.03.19.
 */
public class ReportProvider {

    private final UserService userService;
    private final OrderService orderService;
    private final CountryService countryService;
    private final CityService cityService;

    private ReportGenerator userOrdersTextFileReport;

    public ReportProvider(UserService userService, OrderService orderService,
                          CountryService countryService, CityService cityService) {
        this.userService = userService;
        this.orderService = orderService;
        this.countryService = countryService;
        this.cityService = cityService;

        initReportComponents();
    }

    private void initReportComponents() {
        userOrdersTextFileReport = new UserOrdersReportGenerator(
                userService,
                orderService,
                countryService,
                cityService);
    }

    public File getUserOrdersTextFileReport() throws Exception {
        return userOrdersTextFileReport.createReport();
    }
}
