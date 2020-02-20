package com.example.immutablepojo;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.path.json.JsonPath;

class PersonSerializationTests {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String LINE_1 = "123 Main st.";
    private static final String CITY = "Cincinnati";
    private static final String STATE = "OH";
    private static final String ZIP_CODE = "12345";
    private static final String PHONE_NUMBER = "1235551234";
    private static final String PHONE_TYPE = "cell";
    private static final String EMAIL_ADDRESS = "test@test.com";
    private static final String EMAIL_TYPE = "home";
    private static final String PERSON_JSON = "{\"firstName\":\"" + FIRST_NAME + "\",\"lastName\":\"" + LAST_NAME
            + "\",\"addresses\":[{\"line1\":\"" + LINE_1 + "\",\"line2\":null,\"city\":\"" + CITY + "\",\"state\":\""
            + STATE + "\",\"zipCode\":\"" + ZIP_CODE + "\"}],\"emails\":[{\"address\":\"" + EMAIL_ADDRESS
            + "\",\"type\":\"" + EMAIL_TYPE + "\"}],\"phones\":[{\"number\":\"" + PHONE_NUMBER + "\",\"type\":\""
            + PHONE_TYPE + "\"}]}";

    @Test
    void write() throws JsonProcessingException {
        Person person = new Person.Builder().withFirstName(FIRST_NAME).withLastName(LAST_NAME)
                .withAddresses(Collections.singleton(new Address.Builder().withLine1(LINE_1)
                        .withCity(CITY).withState(STATE).withZipCode(ZIP_CODE).build()))
                .withPhones(Collections.singleton(
                        new Phone.Builder().withNumber(PHONE_NUMBER).withType(PHONE_TYPE).build()))
                .withEmails(Collections.singleton(
                        new Email.Builder().withAddress(EMAIL_ADDRESS).withType(EMAIL_TYPE).build()))
                .build();
        String personJson = OBJECT_MAPPER.writeValueAsString(person);

        Assertions.assertNotNull(personJson, "JSON should not be null");

        JsonPath jsonPath = JsonPath.from(personJson);
        Assertions.assertEquals(FIRST_NAME, jsonPath.get("firstName"), "First names should be equal");
        Assertions.assertEquals(LAST_NAME, jsonPath.get("lastName"), "Last names should be equal");

        Assertions.assertEquals(1, jsonPath.getList("addresses").size(), "Should only have 1 address");
        Assertions.assertEquals(LINE_1, jsonPath.get("addresses[0].line1"), "Line 1 should be equals");
        Assertions.assertNull(jsonPath.get("addresses[0].line2"), "Line 2 should be null");
        Assertions.assertEquals(CITY, jsonPath.get("addresses[0].city"), "City should be equals");
        Assertions.assertEquals(STATE, jsonPath.get("addresses[0].state"), "State should be equals");
        Assertions.assertEquals(ZIP_CODE, jsonPath.get("addresses[0].zipCode"), "Zip code should be equals");

        Assertions.assertEquals(1, jsonPath.getList("emails").size(), "Should only have 1 email");
        Assertions.assertEquals(EMAIL_ADDRESS, jsonPath.get("emails[0].address"), "Email address should be equals");
        Assertions.assertEquals(EMAIL_TYPE, jsonPath.get("emails[0].type"), "Email type should be equals");

        Assertions.assertEquals(1, jsonPath.getList("phones").size(), "Should only have 1 phone");
        Assertions.assertEquals(PHONE_NUMBER, jsonPath.get("phones[0].number"), "Phone number should be equals");
        Assertions.assertEquals(PHONE_TYPE, jsonPath.get("phones[0].type"), "Phone type should be equals");
    }

    @Test
    void read() throws JsonProcessingException {
        Person person = OBJECT_MAPPER.readerFor(Person.class).readValue(PERSON_JSON);

        Assertions.assertNotNull(person);
        Assertions.assertEquals(FIRST_NAME, person.getFirstName(), "First names should be equal");
        Assertions.assertEquals(LAST_NAME, person.getLastName(), "Last names should be equal");

        Collection<Address> addresses = person.getAddresses();
        Assertions.assertEquals(1, addresses.size(), "Should only have 1 address");
        Address address = addresses.iterator().next();
        Assertions.assertEquals(LINE_1, address.getLine1(), "Line 1 should be equals");
        Assertions.assertNull(address.getLine2(), "Line 2 should be null");
        Assertions.assertEquals(CITY, address.getCity(), "City should be equals");
        Assertions.assertEquals(STATE, address.getState(), "State should be equals");
        Assertions.assertEquals(ZIP_CODE, address.getZipCode(), "Zip code should be equals");

        Collection<Email> emails = person.getEmails();
        Assertions.assertEquals(1, emails.size(), "Should only have 1 email");
        Email email = emails.iterator().next();
        Assertions.assertEquals(EMAIL_ADDRESS, email.getAddress(), "Email address must be equals");
        Assertions.assertEquals(EMAIL_TYPE, email.getType(), "Email type must be equals");

        Collection<Phone> phones = person.getPhones();
        Assertions.assertEquals(1, phones.size());
        Phone phone = phones.iterator().next();
        Assertions.assertEquals(PHONE_NUMBER, phone.getNumber(), "Phone number must be equals");
        Assertions.assertEquals(PHONE_TYPE, phone.getType(), "Phone type must be equals");
    }
}
