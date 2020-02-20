package com.example.immutablepojo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.path.json.JsonPath;

class AddressSerializationTests {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String LINE_1 = "123 Main st.";
    private static final String LINE_2 = "apt. 1";
    private static final String CITY = "Cincinnati";
    private static final String STATE = "OH";
    private static final String ZIP_CODE = "12345";
    private static final String ADDRESS_JSON = "{\"line1\":\"" + LINE_1 + "\",\"line2\":\"" + LINE_2
            + "\",\"city\":\"" + CITY + "\",\"state\":\"" + STATE + "\",\"zipCode\":\"" + ZIP_CODE + "\"}";

    // I've added StopWatch in this because I saw a 10x jump in method timing and I wanted to see
    // where it was taking the time
    @Test
    void write() throws JsonProcessingException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("create Address");
        Address address = new Address.Builder().withLine1(LINE_1)
                .withLine2(LINE_2).withCity(CITY).withState(STATE).withZipCode(ZIP_CODE).build();
        stopWatch.stop();
        stopWatch.start("serialize");
        String addressJson = OBJECT_MAPPER.writeValueAsString(address);
        stopWatch.stop();

        Assertions.assertNotNull(addressJson, "JSON should not be null");
        stopWatch.start("jsonPath");
        JsonPath jsonPath = JsonPath.from(addressJson);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        Assertions.assertEquals(LINE_1, jsonPath.get("line1"), "Line 1 should be equals");
        Assertions.assertEquals(LINE_2, jsonPath.get("line2"), "Line 2 should be equals");
        Assertions.assertEquals(CITY, jsonPath.get("city"), "City should be equals");
        Assertions.assertEquals(STATE, jsonPath.get("state"), "State should be equals");
        Assertions.assertEquals(ZIP_CODE, jsonPath.get("zipCode"), "Zip code should be equals");
    }

    @Test
    void read() throws JsonProcessingException {
        Address address = OBJECT_MAPPER.readerFor(Address.class)
                .readValue(ADDRESS_JSON);

        Assertions.assertNotNull(address);
        Assertions.assertEquals(LINE_1, address.getLine1(), "Line 1 should be equals");
        Assertions.assertEquals(LINE_2, address.getLine2(), "Line 2 should be equals");
        Assertions.assertEquals(CITY, address.getCity(), "City should be equals");
        Assertions.assertEquals(STATE, address.getState(), "State should be equals");
        Assertions.assertEquals(ZIP_CODE, address.getZipCode(), "Zip code should be equals");
    }
}
