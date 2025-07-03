package gusgo.registration.application.factory;

import gusgo.registration.application.dto.PersonDTO;
import gusgo.registration.application.interfaces.*;
import gusgo.registration.application.mapper.PersonMapper;
import gusgo.registration.application.resources.ValidationConstants;
import gusgo.registration.domain.interfaces.IndividualPersonDomainService;
import gusgo.registration.domain.model.IndividualPerson;
import gusgo.registration.domain.model.Person;
import gusgo.registration.domain.model.Seller;
import gusgo.registration.rest.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class IndividualPersonFactory implements PersonFactory {

    private final IndividualPersonRepository repository;
    private final IndividualPersonDomainService individualPersonDomainService;
    private final AddressService addressService;
    private final EmailService emailService;
    private final PhoneService phoneService;
    private final SellerService sellerService;

    @Override
    public Person create(PersonDTO personDTO) throws BusinessException {

        Seller seller = sellerService.findByErpId(personDTO.getSeller().getErpId());
        if (seller == null) {
            seller = sellerService.create(personDTO.getSeller());
        }
        IndividualPerson individualPerson = PersonMapper.INSTANCE.toIndividualPerson(personDTO);
        individualPerson.getAddresses().forEach(address -> address.setPerson(individualPerson));
        individualPerson.getEmails().forEach(email -> email.setPerson(individualPerson));
        individualPerson.getPhones().forEach(phone -> phone.setPerson(individualPerson));
        individualPerson.setSeller(seller);

        individualPersonDomainService.validateIndividualPerson(individualPerson);

        repository.save(individualPerson);

        return individualPerson;
    }

    @Override
    public Person update(UUID id, PersonDTO personDTO) throws BusinessException {
        IndividualPerson individualPerson = repository.findById(id).orElseThrow(() -> new BusinessException(ValidationConstants.PERSON_NOT_FOUND));

        Seller seller = sellerService.findByErpId(personDTO.getSeller().getErpId());
        if (seller == null) {
            seller = sellerService.create(personDTO.getSeller());
        }
        addressService.update(individualPerson, personDTO.getAddresses());
        emailService.update(individualPerson, personDTO.getEmails());
        phoneService.update(individualPerson, personDTO.getPhones());

        individualPerson.setName(personDTO.getName());
        individualPerson.setNickname(personDTO.getNickname());
        individualPerson.setErpId(personDTO.getErpId());
        individualPerson.setIsCustomer(personDTO.getIsCustomer() != null && personDTO.getIsCustomer().equals("YES"));
        individualPerson.setIsProvider(personDTO.getIsProvider() != null && personDTO.getIsProvider().equals("YES"));
        individualPerson.setDocumentCPF(personDTO.getMainDocument());
        individualPerson.setDocumentRG(personDTO.getSecondaryDocument());
        individualPerson.setSeller(seller);

        individualPersonDomainService.validateIndividualPerson(individualPerson);

        repository.save(individualPerson);

        return individualPerson;
    }
}
