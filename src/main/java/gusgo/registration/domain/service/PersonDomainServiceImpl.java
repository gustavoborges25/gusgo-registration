package gusgo.registration.domain.service;

import gusgo.registration.application.interfaces.PersonRepository;
import gusgo.registration.application.resources.ValidationConstants;
import gusgo.registration.domain.interfaces.PersonDomainService;
import gusgo.registration.domain.model.Person;
import gusgo.registration.rest.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonDomainServiceImpl implements PersonDomainService {

    private final PersonRepository repository;

    @Override
    public void validatePerson(Person person) throws BusinessException {

        if (person.getName() == null || person.getName().isBlank()) {
            throw new BusinessException(ValidationConstants.NAME_IS_MANDATORY);
        }

        if (person.getErpId() == null || person.getErpId().isBlank()) {
            throw new BusinessException(ValidationConstants.ERP_CODE_IS_MANDATORY);
        }

        Optional<Person> existingPerson = repository.findByErpId(person.getErpId());

        if (existingPerson.isPresent() && existingPerson.get().getId() != person.getId()) {
            throw new BusinessException(ValidationConstants.ERP_CODE_ALREADY_EXISTS);
        }

    }
}
