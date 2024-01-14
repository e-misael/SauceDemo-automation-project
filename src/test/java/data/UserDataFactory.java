package data;

import net.datafaker.Faker;
import org.junit.jupiter.params.provider.Arguments;
import pojo.User;

import java.util.stream.Stream;

public final class UserDataFactory {
    private UserDataFactory(){}
    public static User createStandardUser(){
        var standardUser = new User.UserBuilder()
                                            .setUsername("standard_user")
                                            .setPassword("secret_sauce")
                                                .build();

        return standardUser;
    }
    public static User createLockedUser(){
        var lockedUser = new User.UserBuilder()
                .setUsername("locked_out_user")
                .setPassword("secret_sauce")
                .build();

        return lockedUser;
    }

    public static Stream<Arguments> invalidUsers () {
        User stdUser = UserDataFactory.createStandardUser();
        User lckdUser = UserDataFactory.createLockedUser();

        Faker faker = new Faker();

        return Stream.of(
                Arguments.of(
                        "",
                        stdUser.password(),
                        "Epic sadface: Username is required"
                ),
                Arguments.of(
                        stdUser.username(),
                        "",
                        "Epic sadface: Password is required"
                ),
                Arguments.of(
                        stdUser.username(),
                        faker.internet().password(),
                        "Epic sadface: Username and password do not match any user in this service"
                ),
                Arguments.of(
                        faker.internet().username(),
                        faker.internet().password(),
                        "Epic sadface: Username and password do not match any user in this service"
                ),
                Arguments.of(
                        lckdUser.username(),
                        lckdUser.password(),
                        "Epic sadface: Sorry, this user has been locked out."
                ));
}}
