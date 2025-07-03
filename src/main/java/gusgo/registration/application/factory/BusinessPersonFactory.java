package gusgo.registration.application.factory;

import gusgo.registration.application.dto.PersonDTO;
import gusgo.registration.application.interfaces.*;
import gusgo.registration.application.mapper.PersonMapper;
import gusgo.registration.application.resources.ValidationConstants;
import gusgo.registration.domain.interfaces.BusinessPersonDomainService;
import gusgo.registration.domain.model.BusinessPerson;
import gusgo.registration.domain.model.Person;
import gusgo.registration.domain.model.Seller;
import gusgo.registration.rest.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class BusinessPersonFactory implements PersonFactory {

    private final BusinessPersonRepository repository;
    private final BusinessPersonDomainService businessPersonDomainService;
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
        BusinessPerson businessPerson = PersonMapper.INSTANCE.toBusinessPerson(personDTO);
        businessPerson.getAddresses().forEach(address -> address.setPerson(businessPerson));
        businessPerson.getEmails().forEach(email -> email.setPerson(businessPerson));
        businessPerson.getPhones().forEach(phone -> phone.setPerson(businessPerson));
        businessPerson.setSeller(seller);

        businessPersonDomainService.validateBusinessPerson(businessPerson);

        repository.save(businessPerson);

        return businessPerson;

    }

    @Override
    public Person update(UUID id, PersonDTO personDTO) throws BusinessException {
        BusinessPerson businessPerson = repository.findById(id).orElseThrow(() -> new BusinessException(ValidationConstants.PERSON_NOT_FOUND));

        Seller seller = sellerService.findByErpId(personDTO.getSeller().getErpId());
        if (seller == null) {
            seller = sellerService.create(personDTO.getSeller());
        }

        addressService.update(businessPerson, personDTO.getAddresses());
        emailService.update(businessPerson, personDTO.getEmails());
        phoneService.update(businessPerson, personDTO.getPhones());

        businessPerson.setName(personDTO.getName());
        businessPerson.setNickname(personDTO.getNickname());
        businessPerson.setErpId(personDTO.getErpId());
        businessPerson.setIsCustomer(personDTO.getIsCustomer() != null && personDTO.getIsCustomer().equals("YES"));
        businessPerson.setIsProvider(personDTO.getIsProvider() != null && personDTO.getIsProvider().equals("YES"));
        businessPerson.setDocumentCNPJ(personDTO.getMainDocument());
        businessPerson.setDocumentIE(personDTO.getSecondaryDocument());
        businessPerson.setSeller(seller);

        businessPersonDomainService.validateBusinessPerson(businessPerson);

        repository.save(businessPerson);

        return businessPerson;
    }
}
