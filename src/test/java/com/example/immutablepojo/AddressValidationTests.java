package com.example.immutablepojo;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class AddressValidationTests {

    @ParameterizedTest
    @CsvSource({"123 main st, , city, state, 12345",
            "123 main st, apt 2, city, state, 12345"})
    void createValidAddressPojos(String line1, String line2, String city, String state, String zipCode) {
        Assertions.assertNotNull(new Address.Builder().withLine1(line1).withLine2(line2).withCity(city)
                .withState(state).withZipCode(zipCode).build());
    }

    @ParameterizedTest
    @MethodSource("invalidAddressArgs")
    void createInvalidAddressPojos(String line1, String line2, String city, String state, String zipCode) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Address.Builder().withLine1(line1)
                .withLine2(line2).withCity(city).withState(state).withZipCode(zipCode).build());
    }

    private static Stream<Arguments> invalidAddressArgs() {
        return Stream.of(
                Arguments.of(null, null, null, null, null),
                Arguments.of("", "", "", "", ""),
                Arguments.of("  ", " ", " ", " ", " "),
                Arguments.of("123 main st.", null, null, null, null),
                Arguments.of("123 main st.", null, "", null, null),
                Arguments.of("123 main st.", null, " ", null, null),
                Arguments.of("123 main st.", null, "city", null, null),
                Arguments.of("123 main st.", null, "city", "", null),
                Arguments.of("123 main st.", null, "city", " ", null),
                Arguments.of("123 main st.", null, "city", "state", null),
                Arguments.of("123 main st.", null, "city", "state", ""),
                Arguments.of("123 main st.", null, "city", "state", " ")
        );
    }
}
