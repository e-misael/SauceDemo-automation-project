package data;

import net.datafaker.Faker;
import org.junit.jupiter.params.provider.Arguments;
import pojo.Product;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class OverviewTestData {
    private static final Faker faker = new Faker();

    public static Stream<Arguments> dataForOverviewPageTitleCheck() {
        List<String> productList = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)");
        Collections.shuffle(productList);
        var productName = productList.get(0);

        return Stream.of(
                Arguments.of(
                        productName,
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.address().zipCode(),
                        "Checkout: Overview"
                ));
    }
    public static Stream<Arguments> dataForOverviewFormDataCheck () {
        var product = new Product.ProductBuilder().
                    setName("Sauce Labs Bolt T-Shirt").
                    setDescription("Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.").
                    setPrice("$15.99")
                            .build();

        System.out.println("Aqui "+product.description());
        return Stream.of(
                Arguments.of(
                        "1",
                        product.name(),
                        product.description(),
                        product.price(),
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.address().zipCode(),
                        "Item total: $15.99",
                        "Tax: $1.28",
                        "Total: $17.27"
                ));
    }
    public static Stream<Arguments> dataForOverviewPageCheckingAfterSuccessfulPurchase() {
        List<String> productList = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)");
        Collections.shuffle(productList);
        var productName = productList.get(0);

        return Stream.of(
                Arguments.of(
                        productName,
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.address().zipCode(),
                        "Thank you for your order!"
                ));
    }
}