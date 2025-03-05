package gusgo.tdc.domain.service;

import gusgo.tdc.application.resources.ValidationConstants;
import gusgo.tdc.domain.interfaces.IndividualPersonDomainService;
import gusgo.tdc.domain.model.IndividualPerson;
import gusgo.tdc.rest.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class IndividualPersonDomainServiceImpl implements IndividualPersonDomainService {

    @Override
    public void validateIndividualPerson(IndividualPerson person) throws BusinessException {
        if (person.getDocumentCPF() == null || person.getDocumentCPF().isBlank()) {
            throw new BusinessException(ValidationConstants.DOCUMENT_CPF_IS_REQUIRED);
        }
        // todo: outras validações
    }
}
