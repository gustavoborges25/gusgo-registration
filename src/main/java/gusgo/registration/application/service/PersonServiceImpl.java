package gusgo.registration.application.service;

import gusgo.registration.application.dto.PersonDTO;
import gusgo.registration.application.factory.BusinessPersonFactory;
import gusgo.registration.application.factory.IndividualPersonFactory;
import gusgo.registration.application.interfaces.BusinessPersonRepository;
import gusgo.registration.application.interfaces.IndividualPersonRepository;
import gusgo.registration.application.interfaces.PersonFactory;
import gusgo.registration.application.interfaces.PersonService;
import gusgo.registration.application.mapper.PersonMapper;
import gusgo.registration.application.resources.PersonType;
import gusgo.registration.application.resources.ValidationConstants;
import gusgo.registration.domain.model.BusinessPerson;
import gusgo.registration.domain.model.IndividualPerson;
import gusgo.registration.rest.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

    private final BusinessPersonRepository businessPersonRepository;
    private final IndividualPersonRepository individualPersonRepository;
    private final Map<String, PersonFactory> factoryMap = new HashMap<>();

    public PersonServiceImpl(IndividualPersonFactory individualPersonFactory, BusinessPersonFactory businessPersonFactory,
                             BusinessPersonRepository businessPersonRepository, IndividualPersonRepository individualPersonRepository) {
        this.businessPersonRepository = businessPersonRepository;
        this.individualPersonRepository = individualPersonRepository;
        factoryMap.put(PersonType.INDIVIDUAL.getValue(), individualPersonFactory);
        factoryMap.put(PersonType.BUSINESS.getValue(), businessPersonFactory);
    }

    @Override
    public PersonDTO create(PersonDTO personDTO) throws BusinessException {
        PersonFactory factory = factoryMap.get(personDTO.getType().toUpperCase());

        if (factory == null) {
            throw new BusinessException(ValidationConstants.INVALID_PERSON_TYPE);
        }

        factory.create(personDTO);

        return personDTO;
    }

    @Override
    public List<PersonDTO> findAll() {
        List<PersonDTO> people = new ArrayList<>();

        List<BusinessPerson> businessPeople = businessPersonRepository.findAll();
        List<IndividualPerson> individualPeople = individualPersonRepository.findAll();

        List<PersonDTO> businessPersonDTOs = PersonMapper.INSTANCE.businessPersonToDTO(businessPeople);
        List<PersonDTO> individualPersonDTOs = PersonMapper.INSTANCE.individualPersonToDTO(individualPeople);

        people.addAll(businessPersonDTOs);
        people.addAll(individualPersonDTOs);

        return people;
    }

    @Override
    public PersonDTO update(UUID id, PersonDTO personDTO) {
        PersonFactory factory = factoryMap.get(personDTO.getType().toUpperCase());

        if (factory == null) {
            throw new BusinessException(ValidationConstants.INVALID_PERSON_TYPE);
        }

        factory.update(id, personDTO);

        return personDTO;
    }

    @Override
    public void merge(PersonDTO personDTO) {
        BusinessPerson businessPerson = businessPersonRepository.findByDocumentCNPJ(personDTO.getMainDocument()).orElse(null);
        if (businessPerson != null) {
            update(businessPerson.getId(), personDTO);
            return;
        }

        IndividualPerson individualPerson = individualPersonRepository.findByDocumentCPF(personDTO.getMainDocument()).orElse(null);
        if (individualPerson != null) {
            update(individualPerson.getId(), personDTO);
            return;
        }

        create(personDTO);
    }
}
