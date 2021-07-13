package com.company.modelMapper;


public class SubDTO {
    private String name;

    public SubDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public SubDTO setName(String name) {
        this.name = name;
        return this;
    }
}
