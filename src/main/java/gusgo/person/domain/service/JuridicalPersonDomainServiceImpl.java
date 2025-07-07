package gusgo.person.domain.service;

import gusgo.person.application.interfaces.JuridicalPersonRepository;
import gusgo.person.application.resources.ValidationConstants;
import gusgo.person.domain.interfaces.AddressDomainService;
import gusgo.person.domain.interfaces.JuridicalPersonDomainService;
import gusgo.person.domain.interfaces.EmailDomainService;
import gusgo.person.domain.interfaces.PersonDomainService;
import gusgo.person.domain.interfaces.PhoneDomainService;
import gusgo.person.domain.model.JuridicalPerson;
import gusgo.person.rest.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class JuridicalPersonDomainServiceImpl implements JuridicalPersonDomainService {

    private final PersonDomainService personDomainService;
    private final AddressDomainService addressDomainService;
    private final EmailDomainService emailDomainService;
    private final PhoneDomainService phoneDomainService;
    private final JuridicalPersonRepository repository;

    @Override
    public void validateJuridicalPerson(JuridicalPerson juridicalPerson) throws BusinessException {

        personDomainService.validatePerson(juridicalPerson);
        addressDomainService.validateAddresses(juridicalPerson.getAddresses());
        emailDomainService.validateEmails(juridicalPerson.getEmails());
        phoneDomainService.validatePhones(juridicalPerson.getPhones());

        if (juridicalPerson.getDocumentCNPJ() == null || juridicalPerson.getDocumentCNPJ().isBlank()) {
            throw new BusinessException(ValidationConstants.DOCUMENT_CNPJ_IS_MANDATORY);
        }

        if (juridicalPerson.getIsBranch() && juridicalPerson.getIsCustomer()) {
            throw new BusinessException(ValidationConstants.BRANCH_CANNOT_BE_A_CUSTOMER);
        }

        if (juridicalPerson.getIsBranch() && juridicalPerson.getIsProvider()) {
            throw new BusinessException(ValidationConstants.BRANCH_CANNOT_BE_A_PROVIDER);
        }
    }

    @Override
    public void validateMainDocument(JuridicalPerson juridicalPerson) {
        Optional<JuridicalPerson> existingPerson = repository.findByDocumentCNPJ(juridicalPerson.getDocumentCNPJ());

        if (existingPerson.isPresent()) {
            throw new BusinessException(ValidationConstants.MAIN_DOCUMENT_ALREADY_EXISTS);
        }
    }

    @Override
    public void validateErpId(JuridicalPerson juridicalPerson) {
        Optional<JuridicalPerson> existingPerson = repository.findByErpId(juridicalPerson.getErpId());

        if (existingPerson.isPresent()) {
            throw new BusinessException(ValidationConstants.ERP_CODE_ALREADY_EXISTS);
        }
    }
}
