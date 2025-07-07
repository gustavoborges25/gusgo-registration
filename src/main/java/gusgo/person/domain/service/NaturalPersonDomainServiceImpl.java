package gusgo.person.domain.service;

import gusgo.person.application.interfaces.NaturalPersonRepository;
import gusgo.person.application.resources.ValidationConstants;
import gusgo.person.domain.interfaces.AddressDomainService;
import gusgo.person.domain.interfaces.EmailDomainService;
import gusgo.person.domain.interfaces.NaturalPersonDomainService;
import gusgo.person.domain.interfaces.PersonDomainService;
import gusgo.person.domain.interfaces.PhoneDomainService;
import gusgo.person.domain.model.NaturalPerson;
import gusgo.person.rest.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class NaturalPersonDomainServiceImpl implements NaturalPersonDomainService {

    private final PersonDomainService personDomainService;
    private final AddressDomainService addressDomainService;
    private final EmailDomainService emailDomainService;
    private final PhoneDomainService phoneDomainService;
    private final NaturalPersonRepository repository;

    @Override
    public void validateNaturalPerson(NaturalPerson naturalPerson) throws BusinessException {

        personDomainService.validatePerson(naturalPerson);
        addressDomainService.validateAddresses(naturalPerson.getAddresses());
        emailDomainService.validateEmails(naturalPerson.getEmails());
        phoneDomainService.validatePhones(naturalPerson.getPhones());

        if (naturalPerson.getDocumentCPF() == null || naturalPerson.getDocumentCPF().isBlank()) {
            throw new BusinessException(ValidationConstants.DOCUMENT_CPF_IS_MANDATORY);
        }
    }

    @Override
    public void validateMainDocument(NaturalPerson naturalPerson) {
        Optional<NaturalPerson> existingPerson = repository.findByDocumentCPF(naturalPerson.getDocumentCPF());

        if (existingPerson.isPresent()) {
            throw new BusinessException(ValidationConstants.MAIN_DOCUMENT_ALREADY_EXISTS);
        }
    }

    @Override
    public void validateErpId(NaturalPerson naturalPerson) {
        Optional<NaturalPerson> existingPerson = repository.findByErpId(naturalPerson.getErpId());

        if (existingPerson.isPresent()) {
            throw new BusinessException(ValidationConstants.ERP_CODE_ALREADY_EXISTS);
        }
    }

}
