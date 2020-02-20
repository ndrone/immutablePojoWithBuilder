package com.example.immutablepojo;

import java.util.regex.Pattern;

import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Phone.Builder.class)
public class Phone {
    private static final String PHONE_NUMBER_REGEX = "^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$";
    private static final String ERROR_MESSAGE = "number must match pattern: " + PHONE_NUMBER_REGEX;
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);

    private final String number;
    private final String type;

    private Phone(Builder builder) {
        Assert.hasText(builder.number, ERROR_MESSAGE);
        Assert.isTrue(PHONE_PATTERN.matcher(builder.number).matches(), ERROR_MESSAGE);
        number = builder.number;
        Assert.hasText(builder.type, "type must have text");
        type = builder.type;
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

    public static final class Builder {
        private String number;
        private String type;

        public Builder() {
        }

        public Builder withNumber(String val) {
            number = val;
            return this;
        }

        public Builder withType(String val) {
            type = val;
            return this;
        }

        public Phone build() {
            return new Phone(this);
        }
    }
}
