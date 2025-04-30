package gusgo.person.domain.service;

import gusgo.person.application.resources.ValidationConstants;
import gusgo.person.domain.interfaces.*;
import gusgo.person.domain.model.IndividualPerson;
import gusgo.person.rest.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IndividualPersonDomainServiceImpl implements IndividualPersonDomainService {

    private final PersonDomainService personDomainService;
    private final AddressDomainService addressDomainService;
    private final EmailDomainService emailDomainService;
    private final PhoneDomainService phoneDomainService;

    @Override
    public void validateIndividualPerson(IndividualPerson individualPerson) throws BusinessException {

        personDomainService.validatePerson(individualPerson);
        addressDomainService.validateAddresses(individualPerson.getAddresses());
        emailDomainService.validateEmails(individualPerson.getEmails());
        phoneDomainService.validatePhones(individualPerson.getPhones());

        if (individualPerson.getDocumentCPF() == null || individualPerson.getDocumentCPF().isBlank()) {
            throw new BusinessException(ValidationConstants.DOCUMENT_CPF_IS_MANDATORY);
        }

    }
}
