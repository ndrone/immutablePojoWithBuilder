package com.example.immutablepojo;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PersonValidationTests {

    @ParameterizedTest
    @MethodSource("validPersonArgs")
    void createValidPersonPojos(String firstName, String lastName, Collection<Address> addresses,
                                Collection<Email> emails, Collection<Phone> phones) {
        Assertions.assertNotNull(new Person.Builder().withFirstName(firstName)
                .withLastName(lastName).withAddresses(addresses).withEmails(emails).withPhones(phones).build());
    }

    private static Stream<Arguments> validPersonArgs() {
        return Stream.of(
                Arguments.of("firstName", null, null, null, null),
                Arguments.of(null, "lastName", null, null, null),
                Arguments.of(null, null, Collections.singleton(new Address.Builder()
                        .withLine1("123 Main st").withCity("city").withState("state").withZipCode("12345").build())
                        , null, null),
                Arguments.of(null, null, null, Collections.singleton(
                        new Email("test@test.com", "home")), null),
                Arguments.of(null, null, null, null, Collections.singleton(
                        new Phone("123-456-7890", "cell")))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidPersonArgs")
    void createInvalidPersonPojos(String firstName, String lastName, Collection<Address> addresses,
                                  Collection<Email> emails, Collection<Phone> phones) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Person.Builder().withFirstName(firstName)
                .withLastName(lastName).withAddresses(addresses).withEmails(emails).withPhones(phones).build());
    }

    private static Stream<Arguments> invalidPersonArgs() {
        return Stream.of(
                Arguments.of(null, null, null, null, null),
                Arguments.of("", null, null, null, null),
                Arguments.of("  ", null, null, null, null),
                Arguments.of(null, "", null, null, null),
                Arguments.of(null, " ", null, null, null),
                Arguments.of(null, null, Collections.EMPTY_LIST, null, null),
                Arguments.of(null, null, null, Collections.EMPTY_LIST, null),
                Arguments.of(null, null, null, null, Collections.EMPTY_LIST)
        );
    }
}
