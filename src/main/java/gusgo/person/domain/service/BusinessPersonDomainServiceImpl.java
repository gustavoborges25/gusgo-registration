package gusgo.person.domain.service;

import gusgo.person.application.resources.ValidationConstants;
import gusgo.person.domain.interfaces.*;
import gusgo.person.domain.model.BusinessPerson;
import gusgo.person.rest.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BusinessPersonDomainServiceImpl implements BusinessPersonDomainService {

    private final PersonDomainService personDomainService;
    private final AddressDomainService addressDomainService;
    private final EmailDomainService emailDomainService;
    private final PhoneDomainService phoneDomainService;

    @Override
    public void validateBusinessPerson(BusinessPerson businessPerson) throws BusinessException {

        personDomainService.validatePerson(businessPerson);
        addressDomainService.validateAddresses(businessPerson.getAddresses());
        emailDomainService.validateEmails(businessPerson.getEmails());
        phoneDomainService.validatePhones(businessPerson.getPhones());

        if (businessPerson.getDocumentCNPJ() == null || businessPerson.getDocumentCNPJ().isBlank()) {
            throw new BusinessException(ValidationConstants.DOCUMENT_CNPJ_IS_MANDATORY);
        }

    }
}
