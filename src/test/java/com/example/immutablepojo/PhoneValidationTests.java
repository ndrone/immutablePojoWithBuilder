package com.example.immutablepojo;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class PhoneValidationTests {

    @ParameterizedTest
    @CsvSource({"+11235551234, cell",
            "11235551234, cell",
            "(123)5551234, cell",
            "123-555-1234, cell"})
    void createValidPhonePojos(String phone, String type) {
        Assertions.assertNotNull(new Phone.Builder().withNumber(phone).withType(type).build());
    }

    @ParameterizedTest
    @MethodSource("invalidPhoneArgs")
    void createInvalidPhonePojos(String phone, String type) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Phone.Builder().withNumber(phone).withType(type).build());
    }

    private static Stream<Arguments> invalidPhoneArgs() {
        return Stream.of(
                Arguments.of(null, "home"),
                Arguments.of("", "home"),
                Arguments.of("  ", "home"),
                Arguments.of("not a phone number", "home"),
                Arguments.of("11235551234", null),
                Arguments.of("11235551234", ""),
                Arguments.of("11235551234", " ")
        );
    }
}
