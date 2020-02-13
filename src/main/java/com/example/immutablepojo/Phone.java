package com.example.immutablepojo;

import com.example.immutablepojo.util.Assert;

public class Phone {
    private static final String PHONE_NUMBER_REGEX = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";

    private final String number;
    private final String type;

    public Phone(String number, String type) {
        Assert.hasPattern(PHONE_NUMBER_REGEX, number, "number must match pattern: " + PHONE_NUMBER_REGEX);
        this.number = number;
        Assert.hasText(type, "type must have text");
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "number='" + number + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
