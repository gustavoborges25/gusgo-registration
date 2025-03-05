package gusgo.tdc.application.factory;

import gusgo.tdc.application.dto.PersonDto;
import gusgo.tdc.application.interfaces.PersonFactory;
import gusgo.tdc.domain.interfaces.IndividualPersonDomainService;
import gusgo.tdc.domain.model.IndividualPerson;
import gusgo.tdc.domain.model.Person;
import gusgo.tdc.rest.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IndividualPersonFactory implements PersonFactory {

    private final IndividualPersonDomainService individualPersonDomainService;

    @Override
    public Person create(PersonDto personDto) throws BusinessException {

        IndividualPerson individualPerson = new IndividualPerson();
        individualPerson.setName(personDto.getName());

        individualPersonDomainService.validateIndividualPerson(individualPerson);

        return individualPerson;
    }
}
