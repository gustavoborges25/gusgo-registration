package gusgo.registration.domain.service;

import gusgo.registration.application.interfaces.IndividualPersonRepository;
import gusgo.registration.application.resources.ValidationConstants;
import gusgo.registration.domain.interfaces.*;
import gusgo.registration.domain.model.IndividualPerson;
import gusgo.registration.rest.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class IndividualPersonDomainServiceImpl implements IndividualPersonDomainService {

    private final PersonDomainService personDomainService;
    private final AddressDomainService addressDomainService;
    private final EmailDomainService emailDomainService;
    private final PhoneDomainService phoneDomainService;
    private final IndividualPersonRepository repository;

    @Override
    public void validateIndividualPerson(IndividualPerson individualPerson) throws BusinessException {

        personDomainService.validatePerson(individualPerson);
        addressDomainService.validateAddresses(individualPerson.getAddresses());
        emailDomainService.validateEmails(individualPerson.getEmails());
        phoneDomainService.validatePhones(individualPerson.getPhones());

        if (individualPerson.getDocumentCPF() == null || individualPerson.getDocumentCPF().isBlank()) {
            throw new BusinessException(ValidationConstants.DOCUMENT_CPF_IS_MANDATORY);
        }

        Optional<IndividualPerson> existingPerson = repository.findByDocumentCPF(individualPerson.getDocumentCPF());

        if (existingPerson.isPresent() && existingPerson.get().getId() != individualPerson.getId()) {
            throw new BusinessException(ValidationConstants.MAIN_DOCUMENT_ALREADY_EXISTS);
        }

    }
}
