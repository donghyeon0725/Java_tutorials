package com.company.modelMapper;


public class MemberEntity {
    private String name = "member entity";
    private Entity team;

    public String getName() {
        return name;
    }

    public MemberEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Entity getTeam() {
        return team;
    }

    public MemberEntity setTeam(Entity team) {
        this.team = team;
        return this;
    }
}
