package gusgo.tdc.application.factory;

import gusgo.tdc.application.dto.PersonDto;
import gusgo.tdc.application.interfaces.PersonFactory;
import gusgo.tdc.application.mapper.PersonMapper;
import gusgo.tdc.domain.interfaces.BusinessPersonDomainService;
import gusgo.tdc.domain.model.BusinessPerson;
import gusgo.tdc.domain.model.IndividualPerson;
import gusgo.tdc.domain.model.Person;
import gusgo.tdc.rest.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BusinessPersonFactory implements PersonFactory {

    private final BusinessPersonDomainService businessPersonDomainService;
    @Override
    public Person create(PersonDto personDto) throws BusinessException {
        BusinessPerson businessPerson = PersonMapper.INSTANCE.toBusinessPerson(personDto);
        businessPerson.getAddresses().forEach(address -> address.setPerson(businessPerson));
        businessPersonDomainService.validateBusinessPerson(businessPerson);
        return businessPerson;
    }
}
