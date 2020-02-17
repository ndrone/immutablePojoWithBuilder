package com.example.immutablepojo;


import java.util.regex.Pattern;

import org.springframework.util.Assert;

public class Email {
    private static final String EMAIL_REGEX = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+";
    private static final String ERROR_MESSAGE = "address must match pattern: " + EMAIL_REGEX;
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private final String address;
    private final String type;

    public Email(String address, String type) {
        Assert.hasText(address, ERROR_MESSAGE);
        Assert.isTrue(EMAIL_PATTERN.matcher(address).matches(),
                ERROR_MESSAGE);
        this.address = address;
        Assert.hasText(type, "type must have text");
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Email{" +
                "address='" + address + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
