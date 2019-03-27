import city.domain.City;
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
import storage.initiator.StorageCreator;
import user.domain.BaseUser;
import user.domain.StandardUser;
import user.service.UserService;

import java.util.LinkedList;
import java.util.List;

import static common.solutions.utils.RandomUtils.getRandomInt;

// https://github.com/DmitryYusupov/javacore/blob/master/src/ru/yusdm/javacore
// https://github.com/EricCartman598/Travel/tree/master/src

public class TourismDemo {

    private static class Application {

        static {
            ServiceSupplier.newInstance(StorageType.LINKED_LIST);
        }

        private UserService userService = ServiceSupplier.getInstance().getUserService();
        private CountryService countryService = ServiceSupplier.getInstance().getCountryService();
        private CityService cityService = ServiceSupplier.getInstance().getCityService();
        private OrderService orderService = ServiceSupplier.getInstance().getOrderService();

        private StorageCreator storage = new StorageCreator(countryService);

        private void addUsers() {
            String[] usersAsCsv = new String[]{
                    "Amy      | Lee",
                    "Jacob    | Martin",
                    "John     | Joly",
                    "Simona   | Grims",
                    "Perl     | Laslow",
            };

            Integer id = 0;

            for (String csvUser : usersAsCsv) {
                String[] userAttrs = csvUser.split("\\|");

                int attrIndex = -1;
                userService.add(new StandardUser(userAttrs[++attrIndex].trim(), userAttrs[++attrIndex].trim()));
                                                 // firstName, lastName
            }
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

            for (Pair countryCityData : countriesWithCities) {
                addCountry(countryCityData.getLeft(), countryCityData.getRight());
            }
        }

        private void addCountry(String countryCsv, String[] citiesCsv) {
            String[] attrs = countryCsv.split("\\|");
            int attrIndex = -1;

            BaseCountry country = new CountryWithColdClimate(attrs[++attrIndex].trim(), attrs[++attrIndex].trim());
            country.setCities(new LinkedList<City>());

            for (int i = 0; i < citiesCsv.length; i++) {
                attrIndex = -1;
                attrs = (citiesCsv[i]).split("\\|");

                City city = new City(attrs[++attrIndex].trim(), Integer.parseInt(attrs[++attrIndex].trim()),
                        Boolean.parseBoolean(attrs[++attrIndex].trim()), country);

                country.getCities().add(city);
            }

            countryService.add(country);
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

                for (BaseCountry country: order.getCountries()){
                    if (country.getOrders() == null) {
                        country.setOrders(new LinkedList<Order>());
                    }
                        country.getOrders().add(order);
                }
                for (City city: order.getCities()){
                    if (city.getOrders() == null) {
                        city.setOrders(new LinkedList<Order>());
                    }
                    city.getOrders().add(order);
                }
                if (order.getUser().getOrders() == null) {
                    order.getUser().setOrders(new LinkedList<Order>());
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
            order.getCities().add(country.getCities().get(getRandomInt(0, country.getCities().size() - 1))  );
            order.setPrice(getRandomInt(1, 100000));

            return order;
        }


        public void fillStorage() {
            String file = "./InputData/countriesWithCities.xml";

            try {
                addUsers();
                //addCountriesWithCities();
                fillStorageFromXml(file);
                addOrders();
            } catch (Exception e){
                System.out.println("Problems with filling Storage!");
                e.printStackTrace();
            }
        }

        public void fillStorageFromFile(String file) {
            addUsers();
            //ImportCountries.addCountriesWithCitiesFromFile(file);
        }

        public void fillStorageFromXml(String file) throws Exception{
            addUsers();
            storage.fillStorageWithCountriesAndCities(file, StorageCreator.DataSourceType.XML_FILE, StorageCreator.ParserType.SAX);
        }

        public void printUsers() {
            userService.printAll();
        }

        public void printCountries() {
            countryService.printAll();
        }

        public void printOrders() { orderService.printAll();}

        public void printCities() { cityService.printAll();}

        public void searchOrders() {

            System.out.println("\n\n----------Search orders by country (ASC, COMPLEX)------------\n");

            OrderSearchCondition orderSearchCondition = new OrderSearchCondition();
            orderSearchCondition.setCountry("Colombia");
            orderSearchCondition.setSortType(SortType.COMPLEX);
            orderSearchCondition.setSortDirection(SortDirection.ASC);
            orderSearchCondition.setSortField(OrderSortField.CITY);

            List<Order> searchResult = orderService.search(orderSearchCondition);

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

            //application.deleteUsers();
            //System.out.println();

            ClimateType cT = ClimateType.POLAR;

            /*

            System.out.println("\n--------Test input from file------------\n");

            String file = "./InputData/countriesWithCities.xml";
            try{
                application.fillStorageFromXml(file);
            } catch (Exception e){
                System.out.println("SAX exception");
            }

            System.out.println("\n--------Users------------\n");
            application.printUsers();

            System.out.println("\n--------Countries------------\n");
            application.printCountries();

            application.deleteUsers();
            System.out.println();

            */
    }
}
