import city.domain.City;
import city.service.CityService;
import common.business.application.StorageType;
import common.business.application.servicefactory.ServiceSupplier;
import common.business.search.SortType;
import common.solutions.dataclasses.Pair;
import common.solutions.sequencegenerator.implementation.SimpleSequenceGenerator;
import country.domain.Country;
import country.search.CountrySearchCondition;
import country.service.CountryService;
import order.service.OrderService;
import storage.Storage;
import user.domain.StandardUser;
import user.service.UserService;
import country.domain.ClimateType;

import java.util.LinkedList;
import java.util.List;

// https://github.com/DmitryYusupov/javacore/blob/master/src/ru/yusdm/javacore

public class TourismDemo {

    private static class Application {

        static {
            ServiceSupplier.newInstance(StorageType.LINKED_LIST);
        }

        private UserService userService = ServiceSupplier.getInstance().getUserService();
        private CountryService countryService = ServiceSupplier.getInstance().getCountryService();
        private CityService cityService = ServiceSupplier.getInstance().getCityService();
        private OrderService orderService = ServiceSupplier.getInstance().getOrderService();


        SimpleSequenceGenerator idGenerator = new SimpleSequenceGenerator();


        private Storage storage = new Storage();

        private void addUsers() {
            String[] usersAsCsv = new String[]{
                    "Amy      | Lee",
                    "Jacob    | Martin",
                    "John     | Joly",
                    "Simona   | Grims",
                    "Perl     | Laslow",
            };

            Integer id = 0;
            //userService.setSequenceGenerator(idGenerator); // ???
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

            Country country = new Country(attrs[++attrIndex].trim(), attrs[++attrIndex].trim());
            country.setCities(new LinkedList<>());

            //cityService.setSequenceGenerator(idGenerator);
            //countryService.setSequenceGenerator(idGenerator);

            for (int i = 0; i < citiesCsv.length; i++) {
                attrIndex = -1;
                attrs = (citiesCsv[i]).split("\\|");

                City city = new City(attrs[++attrIndex].trim(), Integer.parseInt(attrs[++attrIndex].trim()),
                        Boolean.parseBoolean(attrs[++attrIndex].trim()), country);

                country.getCities().add(city);
            }

            countryService.add(country);
        }

        public void fillStorage() {
            addUsers();
            addCountriesWithCities();
        }

        public void printUsers() {
            userService.printAll();
        }

        public void printCountries() {
            countryService.printAll();
        }

        public void deleteUsers() {

            userService.deleteByID(1);

            System.out.println("----------Search countries by languages------------");
            CountrySearchCondition countrySearchCondition = new CountrySearchCondition();
            countrySearchCondition.setLanguages("English");
            countrySearchCondition.setSortType(SortType.ASC);
            List<Country> searchResult = countryService.search(countrySearchCondition);

            System.out.println("-----------------------Search result------------------------");
            for (Country country : searchResult) {
                System.out.println(country);
            }
            userService.add(new StandardUser("Amy", "Lee"));
            //userService.deleteByID(33);
        }
    }


        public static void main(String[] args) {


            Application application = new Application();
            application.fillStorage();

            System.out.println("--------Users------------");
            application.printUsers();

            System.out.println("--------Countries------------");
            application.printCountries();

            application.deleteUsers();
            System.out.println();

            ClimateType cT = ClimateType.POLAR;

    }
}
