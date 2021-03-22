package com.company.Optional;

public class Member {
    private Long id;
    private String name;
    private Address address;
    // getters & setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Member(Long id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    static class Builder {
        private Long id;
        private String name;
        private Address address;

        public Long getId() {
            return id;
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Address getAddress() {
            return address;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Member build() {
            return new Member(id, name, address);
        }
    }
}
