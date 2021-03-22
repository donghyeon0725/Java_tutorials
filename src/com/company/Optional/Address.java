package com.company.Optional;

import com.sun.org.apache.bcel.internal.generic.ExceptionThrower;

public class Address {
    private String street;
    private String city;
    private String zipcode;

    public Address(String street, String city, String zipcode) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    static class Builder {
        private String street;
        private String city;
        private String zipcode;

        public String getStreet() {
            return street;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public String getCity() {
            return city;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public String getZipcode() {
            return zipcode;
        }

        public Builder setZipcode(String zipcode) {
            this.zipcode = zipcode;
            return this;
        }

        public Address build() {
            return new Address(street, city, zipcode);
        }

    }
}
