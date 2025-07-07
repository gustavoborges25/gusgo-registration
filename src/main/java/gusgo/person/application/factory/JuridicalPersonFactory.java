package gusgo.person.application.factory;

import gusgo.person.application.dto.PersonDTO;
import gusgo.person.application.interfaces.JuridicalPersonRepository;
import gusgo.person.application.interfaces.PersonFactory;
import gusgo.person.application.mapper.PersonMapper;
import gusgo.person.application.resources.ValidationConstants;
import gusgo.person.domain.interfaces.JuridicalPersonDomainService;
import gusgo.person.domain.model.JuridicalPerson;
import gusgo.person.domain.model.Person;
import gusgo.person.rest.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class JuridicalPersonFactory implements PersonFactory {

    private final JuridicalPersonRepository repository;
    private final JuridicalPersonDomainService juridicalPersonDomainService;

    @Override
    public Person save(Person juridicalPerson) throws BusinessException {
        return repository.save((JuridicalPerson) juridicalPerson);
    }

    @Override
    public Person toEntity(PersonDTO personDTO) {
        return PersonMapper.INSTANCE.toJuridicalPerson(personDTO);
    }

    @Override
    public void setMainDocument(Person juridicalPerson, String mainDocument) throws BusinessException {
        setDocumentCNPJ((JuridicalPerson) juridicalPerson, mainDocument);
    }

    @Override
    public void setSecondaryDocument(Person juridicalPerson, String secondaryDocument) throws BusinessException {
        setDocumentIE((JuridicalPerson) juridicalPerson, secondaryDocument);
    }

    @Override
    public void setIsBranch(Person juridicalPerson, boolean isBranch) throws BusinessException {
        setIsBranch((JuridicalPerson) juridicalPerson, isBranch);
    }

    @Override
    public Person getEntityById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(ValidationConstants.PERSON_NOT_FOUND));
    }

    @Override
    public Person getEntityByMainDocument(String mainDocument) {
        return repository.findByDocumentCNPJ(mainDocument).orElse(null);
    }

    @Override
    public void validateAll(Person juridicalPerson) {
        juridicalPersonDomainService.validateJuridicalPerson((JuridicalPerson) juridicalPerson);
        juridicalPersonDomainService.validateMainDocument((JuridicalPerson) juridicalPerson);
        juridicalPersonDomainService.validateErpId((JuridicalPerson) juridicalPerson);
    }

    @Override
    public void validateToUpdate(Person juridicalPerson) {
        juridicalPersonDomainService.validateJuridicalPerson((JuridicalPerson) juridicalPerson);
    }

    @Override
    public PersonDTO getDTO(Person juridicalPerson) {
        return PersonMapper.INSTANCE.juridicalPersonToDTO((JuridicalPerson) juridicalPerson);
    }

    private void setDocumentCNPJ(JuridicalPerson juridicalPerson, String mainDocument) throws BusinessException {
        juridicalPerson.setDocumentCNPJ(mainDocument);
    }

    private void setDocumentIE(JuridicalPerson juridicalPerson, String secondaryDocument) throws BusinessException {
        juridicalPerson.setDocumentIE(secondaryDocument);
    }

    private void setIsBranch(JuridicalPerson juridicalPerson, boolean isBranch) throws BusinessException {
        juridicalPerson.setIsBranch(isBranch);
    }

}
