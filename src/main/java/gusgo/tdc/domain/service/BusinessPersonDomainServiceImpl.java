package gusgo.tdc.domain.service;

import gusgo.tdc.application.resources.ValidationConstants;
import gusgo.tdc.domain.interfaces.BusinessPersonDomainService;
import gusgo.tdc.domain.model.BusinessPerson;
import gusgo.tdc.rest.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class BusinessPersonDomainServiceImpl implements BusinessPersonDomainService {
    @Override
    public void validateBusinessPerson(BusinessPerson businessPerson) throws BusinessException {
        if (businessPerson.getDocumentCNPJ() == null || businessPerson.getDocumentCNPJ().isBlank()) {
            throw new BusinessException(ValidationConstants.DOCUMENT_CNPJ_IS_REQUIRED);
        }
        // todo: outras validações
    }
}
