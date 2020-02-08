package com.example.immutablepojo;

import java.util.Collection;

public class Person {
    private final String firstName;
    private final String lastName;
    private final Collection<Address> addresses;
    private final Collection<Email> emails;
    private final Collection<Phone> phones;

    private Person(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        addresses = builder.addresses;
        emails = builder.emails;
        phones = builder.phones;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }

    public Collection<Email> getEmails() {
        return emails;
    }

    public Collection<Phone> getPhones() {
        return phones;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addresses=" + addresses +
                ", emails=" + emails +
                ", phones=" + phones +
                '}';
    }


    public static final class Builder {
        private String firstName;
        private String lastName;
        private Collection<Address> addresses;
        private Collection<Email> emails;
        private Collection<Phone> phones;

        public Builder() {
        }

        public Builder withFirstName(String val) {
            firstName = val;
            return this;
        }

        public Builder withLastName(String val) {
            lastName = val;
            return this;
        }

        public Builder withAddresses(Collection<Address> val) {
            addresses = val;
            return this;
        }

        public Builder withEmails(Collection<Email> val) {
            emails = val;
            return this;
        }

        public Builder withPhones(Collection<Phone> val) {
            phones = val;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
