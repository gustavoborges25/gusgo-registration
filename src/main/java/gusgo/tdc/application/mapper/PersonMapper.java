package gusgo.tdc.application.mapper;

import gusgo.tdc.application.dto.PersonDto;
import gusgo.tdc.domain.model.BusinessPerson;
import gusgo.tdc.domain.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(source = "name", target = "name")
    PersonDto toDto(Person person);

    @Mapping(target = "isCustumer", expression = "java(String.valueOf('S').equalsIgnoreCase(personDto.getIsCustumer()))")
    @Mapping(target = "isProvider", expression = "java(String.valueOf('S').equalsIgnoreCase(personDto.getIsProvider()))")
    @Mapping(source = "mainDocument", target = "documentCNPJ")
    @Mapping(source = "secondaryDocument", target = "documentIE")
    BusinessPerson toBusinessPerson(PersonDto personDto);
    List<PersonDto> toDto(List<Person> people);

}
