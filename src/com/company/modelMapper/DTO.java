package com.company.modelMapper;

import java.util.ArrayList;
import java.util.List;


public class DTO {
    private String name;
    private List<String> names;
    private SubDTO sub;
    private List<MemberDTO> members = new ArrayList<>();

    public String getName() {
        return name;
    }

    public DTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getNames() {
        return names;
    }

    public DTO setNames(List<String> names) {
        this.names = names;
        return this;
    }

    public SubDTO getSub() {
        return sub;
    }

    public DTO setSub(SubDTO sub) {
        this.sub = sub;
        return this;
    }

    public List<MemberDTO> getMembers() {
        return members;
    }

    public DTO setMembers(List<MemberDTO> members) {
        this.members = members;
        return this;
    }
}
