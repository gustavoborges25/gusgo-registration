package gusgo.person.application.factory;

import gusgo.person.application.dto.PersonDTO;
import gusgo.person.application.interfaces.NaturalPersonRepository;
import gusgo.person.application.interfaces.PersonFactory;
import gusgo.person.application.mapper.PersonMapper;
import gusgo.person.application.resources.ValidationConstants;
import gusgo.person.domain.interfaces.NaturalPersonDomainService;
import gusgo.person.domain.model.NaturalPerson;
import gusgo.person.domain.model.Person;
import gusgo.person.rest.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class NaturalPersonFactory implements PersonFactory {

    private final NaturalPersonRepository repository;
    private final NaturalPersonDomainService naturalPersonDomainService;

    @Override
    public Person save(Person naturalPerson) throws BusinessException {
        return repository.save((NaturalPerson) naturalPerson);
    }

    @Override
    public Person toEntity(PersonDTO personDTO) {
        return PersonMapper.INSTANCE.toNaturalPerson(personDTO);
    }

    @Override
    public void setMainDocument(Person naturalPerson, String mainDocument) throws BusinessException {
        setDocumentCPF((NaturalPerson) naturalPerson, mainDocument);
    }

    @Override
    public void setSecondaryDocument(Person naturalPerson, String secondaryDocument) throws BusinessException {
        setDocumentRG((NaturalPerson) naturalPerson, secondaryDocument);
    }

    @Override
    public void setIsBranch(Person person, boolean isBranch) {
        // do nothing
    }

    @Override
    public Person getEntityById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(ValidationConstants.PERSON_NOT_FOUND));
    }

    @Override
    public Person getEntityByMainDocument(String mainDocument) {
        return repository.findByDocumentCPF(mainDocument).orElse(null);
    }

    @Override
    public void validateAll(Person naturalPerson) {
        naturalPersonDomainService.validateNaturalPerson((NaturalPerson) naturalPerson);
        naturalPersonDomainService.validateMainDocument((NaturalPerson) naturalPerson);
        naturalPersonDomainService.validateErpId((NaturalPerson) naturalPerson);
    }

    @Override
    public void validateToUpdate(Person naturalPerson) {
        naturalPersonDomainService.validateNaturalPerson((NaturalPerson) naturalPerson);

    }

    @Override
    public PersonDTO getDTO(Person naturalPerson) {
        return PersonMapper.INSTANCE.naturalPersonToDTO((NaturalPerson) naturalPerson);
    }

    private void setDocumentCPF(NaturalPerson naturalPerson, String mainDocument) throws BusinessException {
        naturalPerson.setDocumentCPF(mainDocument);
    }

    private void setDocumentRG(NaturalPerson naturalPerson, String mainDocument) throws BusinessException {
        naturalPerson.setDocumentRG(mainDocument);
    }
}
