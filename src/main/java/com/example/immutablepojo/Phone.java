package com.example.immutablepojo;

public class Phone {
    private final String number;
    private final String type;

    private Phone(Builder builder) {
        number = builder.number;
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
