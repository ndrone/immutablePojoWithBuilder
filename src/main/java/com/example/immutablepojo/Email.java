package com.example.immutablepojo;

public class Email {
    private final String address;
    private final String type;

    private Email(Builder builder) {
        address = builder.address;
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
