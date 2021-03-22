package com.company.Optional;

import java.util.Date;

public class Order {
    private Long id;
    private Date date;
    private Member member;

    public Order(Long id, Date date, Member member) {
        this.id = id;
        this.date = date;
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Order setDate(Date date) {
        this.date = date;
        return this;
    }

    public Member getMember() {
        return member;
    }

    public Order setMember(Member member) {
        this.member = member;
        return this;
    }

    static class Builder {
        private Long id;
        private Date date;
        private Member member;

        public Long getId() {
            return id;
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Date getDate() {
            return date;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Member getMember() {
            return member;
        }

        public Builder setMember(Member member) {
            this.member = member;
            return this;
        }

        public Order build() {
            return new Order(id, date, member);
        }
    }
}
