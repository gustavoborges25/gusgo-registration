package gusgo.registration.domain.service;

import gusgo.registration.application.interfaces.BusinessPersonRepository;
import gusgo.registration.application.resources.ValidationConstants;
import gusgo.registration.domain.interfaces.*;
import gusgo.registration.domain.model.BusinessPerson;
import gusgo.registration.rest.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BusinessPersonDomainServiceImpl implements BusinessPersonDomainService {

    private final PersonDomainService personDomainService;
    private final AddressDomainService addressDomainService;
    private final EmailDomainService emailDomainService;
    private final PhoneDomainService phoneDomainService;
    private final BusinessPersonRepository repository;

    @Override
    public void validateBusinessPerson(BusinessPerson businessPerson) throws BusinessException {

        personDomainService.validatePerson(businessPerson);
        addressDomainService.validateAddresses(businessPerson.getAddresses());
        emailDomainService.validateEmails(businessPerson.getEmails());
        phoneDomainService.validatePhones(businessPerson.getPhones());

        if (businessPerson.getDocumentCNPJ() == null || businessPerson.getDocumentCNPJ().isBlank()) {
            throw new BusinessException(ValidationConstants.DOCUMENT_CNPJ_IS_MANDATORY);
        }

        if (businessPerson.getIsBranch() && businessPerson.getIsCustomer()) {
            throw new BusinessException(ValidationConstants.BRANCH_CANNOT_BE_A_CUSTOMER);
        }

        if (businessPerson.getIsBranch() && businessPerson.getIsProvider()) {
            throw new BusinessException(ValidationConstants.BRANCH_CANNOT_BE_A_PROVIDER);
        }

        Optional<BusinessPerson> existingPerson = repository.findByDocumentCNPJ(businessPerson.getDocumentCNPJ());

        if (existingPerson.isPresent() && existingPerson.get().getId() != businessPerson.getId()) {
            throw new BusinessException(ValidationConstants.MAIN_DOCUMENT_ALREADY_EXISTS);
        }

    }
}
