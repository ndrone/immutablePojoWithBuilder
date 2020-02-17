package com.example.immutablepojo;

import java.util.regex.Pattern;

import org.springframework.util.Assert;

public class Phone {
    private static final String PHONE_NUMBER_REGEX = "^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);

    private final String number;
    private final String type;

    public Phone(String number, String type) {
        Assert.state(PHONE_PATTERN.matcher(number).matches(),
                "number must match pattern: " + PHONE_NUMBER_REGEX);
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
