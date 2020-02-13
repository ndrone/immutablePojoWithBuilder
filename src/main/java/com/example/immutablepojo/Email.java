package com.example.immutablepojo;


import com.example.immutablepojo.util.Assert;

public class Email {
    private static final String EMAIL_REGEX = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+";

    private final String address;
    private final String type;

    public Email(String address, String type) {
        Assert.hasPattern(EMAIL_REGEX, address, "address must match pattern: " + EMAIL_REGEX);
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
