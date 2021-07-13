package com.company.modelMapper;

import java.util.ArrayList;
import java.util.List;

public class Entity {

    private String name;
    private List<String> names;
    private SubEntity sub;
    private List<MemberEntity> members = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Entity setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getNames() {
        return names;
    }

    public Entity setNames(List<String> names) {
        this.names = names;
        return this;
    }

    public SubEntity getSub() {
        return sub;
    }

    public Entity setSub(SubEntity sub) {
        this.sub = sub;
        return this;
    }

    public List<MemberEntity> getMembers() {
        return members;
    }

    public Entity setMembers(List<MemberEntity> members) {
        this.members = members;
        return this;
    }
}
