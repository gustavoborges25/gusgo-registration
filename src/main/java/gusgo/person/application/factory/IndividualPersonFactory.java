package gusgo.person.application.factory;

import gusgo.person.application.dto.PersonDTO;
import gusgo.person.application.interfaces.AddressService;
import gusgo.person.application.interfaces.EmailService;
import gusgo.person.application.interfaces.IndividualPersonRepository;
import gusgo.person.application.interfaces.PersonFactory;
import gusgo.person.application.interfaces.PhoneService;
import gusgo.person.application.mapper.PersonMapper;
import gusgo.person.application.resources.ValidationConstants;
import gusgo.person.domain.interfaces.IndividualPersonDomainService;
import gusgo.person.domain.model.Address;
import gusgo.person.domain.model.Email;
import gusgo.person.domain.model.IndividualPerson;
import gusgo.person.domain.model.Person;
import gusgo.person.domain.model.Phone;
import gusgo.person.rest.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class IndividualPersonFactory implements PersonFactory {

    private final IndividualPersonRepository repository;
    private final IndividualPersonDomainService individualPersonDomainService;
    private final AddressService addressService;
    private final EmailService emailService;
    private final PhoneService phoneService;

    @Override
    public Person create(PersonDTO personDto) throws BusinessException {

        IndividualPerson individualPerson = PersonMapper.INSTANCE.toIndividualPerson(personDto);
        individualPerson.getAddresses().forEach(address -> address.setPerson(individualPerson));
        individualPerson.getEmails().forEach(email -> email.setPerson(individualPerson));
        individualPerson.getPhones().forEach(phone -> phone.setPerson(individualPerson));

        individualPersonDomainService.validateIndividualPerson(individualPerson);

        repository.save(individualPerson);

        return individualPerson;
    }

    @Override
    public Person uodate(UUID id, PersonDTO personDto) throws BusinessException {
        IndividualPerson person = repository.findById(id).orElseThrow(() -> new BusinessException(ValidationConstants.PERSON_NOT_FOUND));

        List<Address> addressesUpdated = addressService.update(person, personDto.getAddresses());
        List<Email> emailsUpdated = emailService.update(person, personDto.getEmails());
        List<Phone> phonesUpdated = phoneService.update(person, personDto.getPhones());

        person.setAddresses(addressesUpdated);
        person.setEmails(emailsUpdated);
        person.setPhones(phonesUpdated);

        person.setName(personDto.getName());
        person.setNickname(personDto.getNickname());
        person.setErpCode(personDto.getErpCode());
        person.setIsCustomer(personDto.getIsCustomer().equals("YES"));
        person.setIsProvider(personDto.getIsProvider().equals("YES"));
        person.setDocumentCPF(personDto.getMainDocument());
        person.setDocumentRG(personDto.getSecondaryDocument());

        repository.save(person);

        return person;
    }
}
