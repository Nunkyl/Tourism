import city.domain.City;
import city.search.CitySearchCondition;
import city.service.CityService;
import common.business.application.StorageType;
import common.business.application.servicefactory.ServiceSupplier;
import common.business.search.Paginator;
import common.business.search.SortDirection;
import common.business.search.SortType;
import common.solutions.dataclasses.Pair;
import country.domain.ClimateCategory;
import country.domain.ClimateType;
import country.domain.BaseCountry;
import country.domain.CountryWithColdClimate;
import country.search.CountrySearchCondition;
import country.search.CountrySortField;
import country.service.CountryService;
import order.domain.Order;
import order.search.OrderSearchCondition;
import order.search.OrderSortField;
import order.service.OrderService;
import reporting.ReportGenerator;
import reporting.ReportProvider;
import storage.initiator.DataSourceType;
import storage.initiator.ParserType;
import storage.initiator.ThreadInitiator;
import user.domain.*;
import user.search.BaseUserSearchCondition;
import user.search.VIPUserSearchCondition;
import user.service.UserService;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static common.solutions.utils.RandomUtils.getRandomInt;

public class TourismDemo {

    private static class Application {

        static {
            ServiceSupplier.newInstance(StorageType.LINKED_LIST);
        }

        private UserService userService = ServiceSupplier.getInstance().getUserService();
        private CountryService countryService = ServiceSupplier.getInstance().getCountryService();
        private CityService cityService = ServiceSupplier.getInstance().getCityService();
        private OrderService orderService = ServiceSupplier.getInstance().getOrderService();

        private void addUsers() {
            String[] usersAsCsv = new String[]{
                    "Amy      | Lee",
                    "Jacob    | Martin",
                    "John     | Joly",
                    "Simona   | Grims",
                    "Perl     | Laslow",
            };


            String[] userAttrs = usersAsCsv[0].split("\\|");
            StandardUser mainGuardian = new StandardUser(userAttrs[0].trim(), userAttrs[1].trim(),
                    UserCategory.STANDARD, "70 127346");
            userService.add(mainGuardian);

            userAttrs = usersAsCsv[1].split("\\|");
            userService.add(new ChildUser(userAttrs[0].trim(), userAttrs[1].trim(), UserCategory.CHILD, mainGuardian));

            userAttrs = usersAsCsv[2].split("\\|");
            userService.add(new ChildUser(userAttrs[0].trim(), userAttrs[1].trim(), UserCategory.CHILD, mainGuardian));

            userAttrs = usersAsCsv[3].split("\\|");
            userService.add(new VIPUser(userAttrs[0].trim(), userAttrs[1].trim(), UserCategory.VIP, "90 765234"));

            userAttrs = usersAsCsv[4].split("\\|");
            userService.add(new VIPUser(userAttrs[0].trim(), userAttrs[1].trim(), UserCategory.VIP, "50 284901"));

            /*
            Integer id = 0;

            for (String csvUser : usersAsCsv) {
                String[] userAttrs = csvUser.split("\\|");

                int attrIndex = -1;
                userService.add(new StandardUser(userAttrs[++attrIndex].trim(), userAttrs[++attrIndex].trim()));
                                                 // firstName, lastName
            }
            */
        }

        private void addCountriesWithCities() {
            Pair[] countriesWithCities = new Pair[]{
                    new Pair("England | English",
                            new String[]{
                                    "London    | 8100000 | True ",
                                    "Liverpool | 470000  | False",
                                    "Bristol   | 430000  | False"
                            }
                    ),
                    new Pair("Russia | Russian",
                            new String[]{
                                    "Moscow           | 12000000 | True ",
                                    "Saint-Petersburg | 5000000  | False",
                                    "Kazan            | 1200000  | False"
                            }
                    ),

                    new Pair("Canada | English, French",
                            new String[]{
                                    "Toronto   | 2700000 | True ",
                                    "Montreal  | 1700000 | False",
                                    "Edmonton  | 970000  | False"
                            }
                    ),

                    new Pair("Colombia | Spanish, English",
                            new String[]{
                                    "Medellin   | 2500000 | False ",
                                    "Bogota     | 8100000 | True",
                                    "Cali       | 2400000 | False"
                            }
                    ),

                    new Pair("Australia | English",
                            new String[]{
                                    "Sydney     | 4800000 | False ",
                                    "Melbourne  | 4400000 | False",
                                    "Perth      | 1900000 | False"
                            }
                    )
            };

            List<BaseCountry> result = new LinkedList<>();
            for (Pair countryCityData : countriesWithCities) {
                //countryService.add(createCountry(countryCityData.getLeft(), countryCityData.getRight()));
                result.add(createCountry(countryCityData.getLeft(), countryCityData.getRight()));
            }
            countryService.add(result);
        }

        private BaseCountry createCountry(String countryCsv, String[] citiesCsv) {
            String[] attrs = countryCsv.split("\\|");
            int attrIndex = -1;

            BaseCountry country = new CountryWithColdClimate(attrs[++attrIndex].trim(), attrs[++attrIndex].trim());
            country.setCities(new LinkedList<>());

            for (int i = 0; i < citiesCsv.length; i++) {
                attrIndex = -1;
                attrs = (citiesCsv[i]).split("\\|");

                City city = new City(attrs[++attrIndex].trim(), Integer.parseInt(attrs[++attrIndex].trim()),
                        Boolean.parseBoolean(attrs[++attrIndex].trim()), country);

                country.getCities().add(city);
            }

            return country;
        }

        private void addOrders() {
            List<BaseCountry> countries = countryService.findAll();
            List<BaseUser> users = userService.findAll();

            List<Order> orders = new LinkedList<>();
            int i = 0;
            for (BaseUser user : users) {
                i++;
                orders.add(prepareOrderForUser(user, countries));

                if (i % 2 == 0) {
                    orders.add(prepareOrderForUser(user, countries));
                }
            }

            for (Order order : orders) {
                orderService.add(order);

                for (BaseCountry country : order.getCountries()) {
                    if (country.getOrders() == null) {
                        country.setOrders(new LinkedList<>());
                    }
                    country.getOrders().add(order);
                }
                for (City city : order.getCities()) {
                    if (city.getOrders() == null) {
                        city.setOrders(new LinkedList<>());
                    }
                    city.getOrders().add(order);
                }
                if (order.getUser().getOrders() == null) {
                    order.getUser().setOrders(new LinkedList<>());
                }
                order.getUser().getOrders().add(order);
            }
        }

        private Order prepareOrderForUser(BaseUser user, List<BaseCountry> countries) {
            Order order = new Order();
            order.setUser(user);
            BaseCountry country = countries.get(getRandomInt(0, countries.size() - 1));
            order.setCountries(new LinkedList<BaseCountry>());
            order.getCountries().add(country);
            order.setCities(new LinkedList<City>());
            order.getCities().add(country.getCities().get(getRandomInt(0, country.getCities().size() - 1)));
            order.setPrice(getRandomInt(1, 100000));

            return order;
        }

        public void fillStorage() {
            try {
                addUsers();
                //addCountriesWithCities();
                fillStorageInParallel();
                addOrders();
            } catch (Exception e) {
                System.out.println("Problems with filling Storage!");
            }
        }

        public void fillStorageFromXmlInParallel(List<File> files, DataSourceType sT, ParserType pT) throws Exception {
            ThreadInitiator threadInitiator = new ThreadInitiator(countryService);
            threadInitiator.initStorageWithMarksAndModels(files, sT, pT);
        }

        public void fillStorageInParallel() {

            List<File> files = new ArrayList<>(Arrays.asList(new File("./InputData/countriesWithCitiesPart1.xml"),
                    new File("./InputData/countriesWithCitiesPart2.xml")));
            DataSourceType sourceType = DataSourceType.XML_FILE;
            ParserType parserType = ParserType.SAX;

            try {
                fillStorageFromXmlInParallel(files, sourceType, parserType);
            } catch (Exception e) {
                System.out.println("fillStorageFromXmlInParallel error!");
                e.printStackTrace();
            }
        }

        public void printUsers() {
            userService.printAll();
        }

        public void printCountries() {
            countryService.printAll();
        }

        public void printOrders() {
            orderService.printAll();
        }

        public void printCities() {
            cityService.printAll();
        }

        public void searchOrders() {

            System.out.println("\n\n----------Search orders by country (ASC, COMPLEX)------------\n");

            OrderSearchCondition orderSearchCondition = new OrderSearchCondition();
            orderSearchCondition.setCountry("Colombia");
            orderSearchCondition.setSortType(SortType.COMPLEX);
            orderSearchCondition.setSortDirection(SortDirection.ASC);
            orderSearchCondition.setSortField(OrderSortField.CITY);
            orderSearchCondition.setPaginator(new Paginator((0)));

            List<? extends Order> searchResult = orderService.search(orderSearchCondition);

            for (Order order : searchResult) {
                System.out.println(order);
            }
        }

        public void searchCountries() {

            System.out.println("\n\n----------Search countries by language (ASC, COMPLEX, PAGINATOR)------------\n");

            CountrySearchCondition countrySearchCondition = new CountrySearchCondition();
            countrySearchCondition.setLanguage("English");
            countrySearchCondition.setSortType(SortType.COMPLEX);
            countrySearchCondition.setSortDirection(SortDirection.ASC);
            countrySearchCondition.setSortField(CountrySortField.NAME);
            countrySearchCondition.setDiscriminator(ClimateCategory.NOT_SET);
            countrySearchCondition.setPaginator(new Paginator((0)));

            List<? extends BaseCountry> searchResult = countryService.search(countrySearchCondition);

            for (BaseCountry country : searchResult) {
                System.out.println(country);
            }

            countrySearchCondition.setPaginator(new Paginator((2)));
            searchResult = countryService.search(countrySearchCondition);
            for (BaseCountry country : searchResult) {
                System.out.println(country);
            }
        }

        public void searchUsers() {

            System.out.println("\n\n----------Search users by full name (PAGINATOR)------------\n");

            BaseUserSearchCondition userSearchCondition = new BaseUserSearchCondition();
            userSearchCondition.setFirstName("Amy");
            userSearchCondition.setLastName("Lee");
            userSearchCondition.setDiscriminator(UserCategory.NOT_SET);
            userSearchCondition.setPaginator(new Paginator((0)));

            List<? extends BaseUser> searchResult = userService.search(userSearchCondition);

            for (BaseUser user : searchResult) {
                System.out.println(user);
            }

            System.out.println("\n\n----------Search users by passportID (PAGINATOR)------------\n");

            VIPUserSearchCondition userSearchCondition2 = new VIPUserSearchCondition();
            userSearchCondition2.setPassportID("90 765234");
            userSearchCondition2.setDiscriminator(UserCategory.VIP);
            userSearchCondition2.setPaginator(new Paginator((0)));

            searchResult = userService.search(userSearchCondition2);

            for (BaseUser user : searchResult) {
                System.out.println(user);
            }
        }

        public void searchCities() {

            System.out.println("\n\n----------Search cities by name (PAGINATOR)------------\n");

            CitySearchCondition citySearchCondition = new CitySearchCondition();
            citySearchCondition.setCountryName("Colombia");
            citySearchCondition.setPaginator(new Paginator((0)));

            List<? extends City> searchResult = cityService.search(citySearchCondition);

            for (City city : searchResult) {
                System.out.println(city);
            }
        }

        public void createUserOrdersReports(){
            try {
                ReportProvider reportProvider = new ReportProvider(userService, orderService, countryService, cityService);
                reportProvider.getUserOrdersTextFileReport();
            } catch (Exception e){
                System.out.println("Problems with generating report!");
            }
        }

        /*
        public void deleteUsers() {

            userService.deleteByID(1);

            System.out.println("\n----------Search countries by languages------------\n");
            CountrySearchCondition countrySearchCondition = new CountrySearchCondition();
            //countrySearchCondition.setLanguages("English");
            //countrySearchCondition.setSortType(SortDirection.ASC);
            List<BaseCountry> searchResult = countryService.search(countrySearchCondition);

            System.out.println("-----------------------Search result------------------------");
            for (BaseCountry country : searchResult) {
                System.out.println(country);
            }
            userService.add(new StandardUser("Amy", "Lee"));
            //userService.deleteByID(33);
        }
        */
    }

    public static void main(String[] args) {

        Application application = new Application();

        application.fillStorage();

        System.out.println("\n--------Users------------\n");
        application.printUsers();

        System.out.println("\n--------Countries------------\n");
        application.printCountries();

        System.out.println("\n--------Orders------------\n");
        application.printOrders();

        System.out.println("\n--------Cities------------\n");
        application.printCities();

        application.searchOrders();

        application.searchCountries();

        application.searchUsers();

        application.searchCities();

        //System.out.println("\n--------Delete city that still has orders------------\n");

        application.createUserOrdersReports();

        //application.deleteUsers();
        //System.out.println();

    }
}
