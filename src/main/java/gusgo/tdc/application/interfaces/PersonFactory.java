package gusgo.tdc.application.interfaces;

import gusgo.tdc.application.dto.PersonDto;
import gusgo.tdc.domain.model.Person;
import gusgo.tdc.rest.exception.BusinessException;

public interface PersonFactory {

    Person create(PersonDto personDto) throws BusinessException;
}
