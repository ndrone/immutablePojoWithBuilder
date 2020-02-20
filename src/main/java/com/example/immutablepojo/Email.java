package com.example.immutablepojo;


import java.util.regex.Pattern;

import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Email.Builder.class)
public class Email {
    private static final String EMAIL_REGEX = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+";
    private static final String ERROR_MESSAGE = "address must match pattern: " + EMAIL_REGEX;
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private final String address;
    private final String type;

    private Email(Builder builder) {
        Assert.hasText(builder.address, ERROR_MESSAGE);
        Assert.isTrue(EMAIL_PATTERN.matcher(builder.address).matches(),
                ERROR_MESSAGE);
        address = builder.address;
        Assert.hasText(builder.type, "type must have text");
        type = builder.type;
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

    public static final class Builder {
        private String address;
        private String type;

        public Builder() {
        }

        public Builder withAddress(String val) {
            address = val;
            return this;
        }

        public Builder withType(String val) {
            type = val;
            return this;
        }

        public Email build() {
            return new Email(this);
        }
    }
}
