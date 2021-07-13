package com.company.modelMapper;


public class MemberDTO {
    private String name = "member dto";
    private DTO team;

    public String getName() {
        return name;
    }

    public MemberDTO setName(String name) {
        this.name = name;
        return this;
    }

    public DTO getTeam() {
        return team;
    }

    public MemberDTO setTeam(DTO team) {
        this.team = team;
        return this;
    }
}
