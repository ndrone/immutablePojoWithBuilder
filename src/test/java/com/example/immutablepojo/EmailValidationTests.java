package com.example.immutablepojo;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

class EmailValidationTests {

    @ParameterizedTest
    @CsvSource({"blah@blah.com, home",
            "test.test@testing.com, home"})
    void createValidEmailPojos(String email, String type) {
        Assertions.assertNotNull(new Email.Builder().withAddress(email).withType(type).build());
    }

    @ParameterizedTest
    @MethodSource("invalidEmailArgs")
    void createInvalidEmailPojos(String email, String type) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Email.Builder().withAddress(email).withType(type).build());
    }

    private static Stream<Arguments> invalidEmailArgs() {
        return Stream.of(
                Arguments.of(null, "home"),
                Arguments.of("", "home"),
                Arguments.of("  ", "home"),
                Arguments.of("not blank", "home"),
                Arguments.of("test@test.com", null),
                Arguments.of("test@test.com", ""),
                Arguments.of("test@test.com", " ")
        );
    }
}
