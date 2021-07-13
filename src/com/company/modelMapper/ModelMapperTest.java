package com.company.modelMapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Arrays;

public class ModelMapperTest {
    public static void main(String[] args) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE).setFieldMatchingEnabled(true).setMatchingStrategy(MatchingStrategies.LOOSE);

        DTO dto = new DTO();
        dto.setName("이름");
        dto.setNames(Arrays.asList(
                "이름1",
                "이름2",
                "이름3"
        ));
        dto.setSub(new SubDTO("서브"));
        dto.getMembers().add(new MemberDTO());

        Entity entity = modelMapper.map(dto, Entity.class);
        System.out.println("entity.getName() = " + entity.getName());
        System.out.println("entity.getNames() = " + entity.getNames().get(0));
        System.out.println("entity.getNames() = " + entity.getNames().get(1));
        System.out.println("entity.getNames() = " + entity.getNames().get(2));
        System.out.println("entity.getNames() = " + entity.getSub().getName());
        System.out.println("entity.getNames() = " + entity.getMembers().get(0).getName());
    }
}
