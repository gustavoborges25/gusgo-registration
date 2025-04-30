package gusgo.person.domain.service;

import gusgo.person.application.interfaces.PersonRepository;
import gusgo.person.application.resources.ValidationConstants;
import gusgo.person.domain.interfaces.PersonDomainService;
import gusgo.person.domain.model.Person;
import gusgo.person.rest.exception.BusinessException;
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

        if (person.getErpCode() == null || person.getErpCode().isBlank()) {
            throw new BusinessException(ValidationConstants.ERP_CODE_IS_MANDATORY);
        }

        Optional<Person> existingPerson = repository.findByErpCode(person.getErpCode());

        if (existingPerson.isPresent() && existingPerson.get().getId() != person.getId()) {
            throw new BusinessException(ValidationConstants.ERP_CODE_ALREADY_EXISTS);
        }

    }
}
