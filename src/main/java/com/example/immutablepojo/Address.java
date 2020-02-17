package com.example.immutablepojo;

import org.springframework.util.Assert;

public class Address {

    private final String line1;
    private final String line2;
    private final String city;
    private final String state;
    private final String zipCode;

    private Address(Builder builder) {
        Assert.hasText(builder.line1, "line1 must have text");
        line1 = builder.line1;
        line2 = builder.line2;
        Assert.hasText(builder.city, "city must have text");
        city = builder.city;
        Assert.hasText(builder.state, "state must have text");
        state = builder.state;
        Assert.hasText(builder.zipCode, "zipCode must have text");
        zipCode = builder.zipCode;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }


    public static final class Builder {
        private String line1;
        private String line2;
        private String city;
        private String state;
        private String zipCode;

        public Builder() {
        }

        public Builder withLine1(String val) {
            line1 = val;
            return this;
        }

        public Builder withLine2(String val) {
            line2 = val;
            return this;
        }

        public Builder withCity(String val) {
            city = val;
            return this;
        }

        public Builder withState(String val) {
            state = val;
            return this;
        }

        public Builder withZipCode(String val) {
            zipCode = val;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
