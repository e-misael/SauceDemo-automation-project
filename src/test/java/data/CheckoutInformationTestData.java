package data;

import net.datafaker.Faker;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class CheckoutInformationTestData {
    private static final Faker faker = new Faker();

    public static Stream<Arguments> dataForCheckoutFormValidation () {
        var productName = "Sauce Labs Backpack";
        return Stream.of(
                Arguments.of(
                        productName,
                        "",
                        "",
                        "",
                        "Error: First Name is required"
                ),
                Arguments.of(
                        productName,
                        "",
                        faker.name().lastName(),
                        faker.address().zipCode(),
                        "Error: First Name is required"
                ),
                Arguments.of(
                        productName,
                        faker.name().firstName(),
                        "",
                        faker.address().zipCode(),
                        "Error: Last Name is required"
                ),
                Arguments.of(
                        productName,
                        faker.name().firstName(),
                        faker.name().fullName(),
                        "",
                        "Error: Postal Code is required"
                ));
    }
}