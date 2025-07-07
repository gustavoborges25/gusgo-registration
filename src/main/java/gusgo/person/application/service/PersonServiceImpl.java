package gusgo.person.application.service;

import gusgo.person.application.dto.PersonDTO;
import gusgo.person.application.factory.JuridicalPersonFactory;
import gusgo.person.application.factory.NaturalPersonFactory;
import gusgo.person.application.interfaces.JuridicalPersonRepository;
import gusgo.person.application.interfaces.NaturalPersonRepository;
import gusgo.person.application.interfaces.PersonFactory;
import gusgo.person.application.interfaces.PersonService;
import gusgo.person.application.interfaces.SellerService;
import gusgo.person.application.mapper.PersonMapper;
import gusgo.person.application.resources.PersonType;
import gusgo.person.application.resources.ValidationConstants;
import gusgo.person.domain.model.JuridicalPerson;
import gusgo.person.domain.model.NaturalPerson;
import gusgo.person.domain.model.Person;
import gusgo.person.domain.model.Seller;
import gusgo.person.rest.exception.BusinessException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

    private final JuridicalPersonRepository juridicalPersonRepository;
    private final NaturalPersonRepository naturalPersonRepository;
    private final Map<String, PersonFactory> factoryMap = new HashMap<>();
    private final SellerService sellerService;

    public PersonServiceImpl(NaturalPersonFactory naturalPersonFactory, JuridicalPersonFactory juridicalPersonFactory,
                             JuridicalPersonRepository juridicalPersonRepository, NaturalPersonRepository naturalPersonRepository,
                             SellerService sellerService) {
        this.juridicalPersonRepository = juridicalPersonRepository;
        this.naturalPersonRepository = naturalPersonRepository;
        this.sellerService = sellerService;
        factoryMap.put(PersonType.NATURAL.getValue(), naturalPersonFactory);
        factoryMap.put(PersonType.JURIDICAL.getValue(), juridicalPersonFactory);
    }

    @Transactional
    @Override
    public PersonDTO create(PersonDTO personDTO) throws BusinessException {
        PersonFactory factory = factoryMap.get(personDTO.getType().toUpperCase());

        if (factory == null) {
            throw new BusinessException(ValidationConstants.INVALID_PERSON_TYPE);
        }

        Person person = factory.toEntity(personDTO);

        Seller seller = getSeller(personDTO);
        person.setSeller(seller);

        setAddresses(personDTO, person);
        setEmails(personDTO, person);
        setPhones(personDTO, person);

        factory.validateAll(person);

        Person personSaved = factory.save(person);

        return factory.getDTO(personSaved);
    }

    @Transactional
    @Override
    public PersonDTO update(UUID id, PersonDTO personDTO) {
        PersonFactory factory = factoryMap.get(personDTO.getType().toUpperCase());

        if (factory == null) {
            throw new BusinessException(ValidationConstants.INVALID_PERSON_TYPE);
        }

        Person person = factory.getEntityById(id);
        Seller seller = getSeller(personDTO);
        person.setSeller(seller);

        person.setAddresses(new ArrayList<>());
        setAddresses(personDTO, person);
        person.setPhones(new ArrayList<>());
        setPhones(personDTO, person);
        person.setEmails(new ArrayList<>());
        setEmails(personDTO, person);

        person.setName(personDTO.getName());
        person.setNickname(personDTO.getNickname());
        person.setErpId(personDTO.getErpId());
        person.setIsCustomer(personDTO.getIsCustomer() != null && personDTO.getIsCustomer().equals("YES"));
        person.setIsProvider(personDTO.getIsProvider() != null && personDTO.getIsProvider().equals("YES"));
        person.setStatus(personDTO.getStatus());

        factory.setMainDocument(person, personDTO.getMainDocument());
        factory.setSecondaryDocument(person, personDTO.getSecondaryDocument());
        factory.setIsBranch(person, personDTO.getIsBranch() != null && personDTO.getIsBranch().equals("YES"));

        factory.validateToUpdate(person);

        Person personSaved = factory.save(person);

        return factory.getDTO(personSaved);

    }

    @Override
    public void merge(PersonDTO personDTO) {
        PersonFactory factory = factoryMap.get(personDTO.getType().toUpperCase());

        if (factory == null) {
            throw new BusinessException(ValidationConstants.INVALID_PERSON_TYPE);
        }

        Person person = factory.getEntityByMainDocument(personDTO.getMainDocument());

        if (person != null) {
            update(person.getId(), personDTO);
            return;
        }

        create(personDTO);
    }

    @Override
    public List<PersonDTO> findAll() {
        List<PersonDTO> people = new ArrayList<>();

        List<JuridicalPerson> juridicalPeople = juridicalPersonRepository.findAll();
        List<NaturalPerson> naturalPeople = naturalPersonRepository.findAll();

        List<PersonDTO> juridicalPersonToDTO = PersonMapper.INSTANCE.juridicalPersonToDTO(juridicalPeople);
        List<PersonDTO> naturalPersonToDTO = PersonMapper.INSTANCE.naturalPersonToDTO(naturalPeople);

        people.addAll(juridicalPersonToDTO);
        people.addAll(naturalPersonToDTO);

        return people;
    }

    private static void setPhones(PersonDTO personDTO, Person person) {
        if (personDTO.getPhones() != null) {
            personDTO.getPhones()
                    .forEach(phoneDTO -> person.addPhone(PersonMapper.INSTANCE.toPhone(phoneDTO)));
        }
    }

    private static void setEmails(PersonDTO personDTO, Person person) {
        if (personDTO.getEmails() != null) {
            personDTO.getEmails()
                    .forEach(emailDTO -> person.addEmail(PersonMapper.INSTANCE.toEmail(emailDTO)));
        }
    }

    private static void setAddresses(PersonDTO personDTO, Person person) {
        if (personDTO.getAddresses() != null) {
            personDTO.getAddresses()
                    .forEach(addressDTO -> person.addAddress(PersonMapper.INSTANCE.toAddress(addressDTO)));
        }
    }

    private Seller getSeller(PersonDTO personDTO) {
        Seller seller = sellerService.findByErpId(personDTO.getSeller().getErpId());
        if (seller == null) {
            seller = sellerService.create(personDTO.getSeller());
        }
        return seller;
    }

}
