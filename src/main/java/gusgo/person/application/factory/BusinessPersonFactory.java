package gusgo.person.application.factory;

import gusgo.person.application.dto.PersonDTO;
import gusgo.person.application.interfaces.AddressService;
import gusgo.person.application.interfaces.BusinessPersonRepository;
import gusgo.person.application.interfaces.EmailService;
import gusgo.person.application.interfaces.PersonFactory;
import gusgo.person.application.interfaces.PhoneService;
import gusgo.person.application.mapper.PersonMapper;
import gusgo.person.application.resources.ValidationConstants;
import gusgo.person.domain.interfaces.BusinessPersonDomainService;
import gusgo.person.domain.model.Address;
import gusgo.person.domain.model.BusinessPerson;
import gusgo.person.domain.model.Email;
import gusgo.person.domain.model.Person;
import gusgo.person.domain.model.Phone;
import gusgo.person.rest.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class BusinessPersonFactory implements PersonFactory {

    private final BusinessPersonRepository repository;
    private final BusinessPersonDomainService businessPersonDomainService;
    private final AddressService addressService;
    private final EmailService emailService;
    private final PhoneService phoneService;

    @Override
    public Person create(PersonDTO personDto) throws BusinessException {

        BusinessPerson businessPerson = PersonMapper.INSTANCE.toBusinessPerson(personDto);
        businessPerson.getAddresses().forEach(address -> address.setPerson(businessPerson));
        businessPerson.getEmails().forEach(email -> email.setPerson(businessPerson));
        businessPerson.getPhones().forEach(phone -> phone.setPerson(businessPerson));

        businessPersonDomainService.validateBusinessPerson(businessPerson);

        repository.save(businessPerson);

        return businessPerson;

    }

    @Override
    public Person uodate(UUID id, PersonDTO personDto) throws BusinessException {
        BusinessPerson person = repository.findById(id).orElseThrow(() -> new BusinessException(ValidationConstants.PERSON_NOT_FOUND));

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
        person.setDocumentCNPJ(personDto.getMainDocument());
        person.setDocumentIE(personDto.getSecondaryDocument());

        repository.save(person);

        return person;
    }
}
