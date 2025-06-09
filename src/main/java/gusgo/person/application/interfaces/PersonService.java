package gusgo.person.application.interfaces;

import gusgo.person.application.dto.PersonDTO;
import gusgo.person.rest.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    PersonDTO create(PersonDTO personDto) throws BusinessException;

    List<PersonDTO> findAll();

    void uploadBp(MultipartFile file) throws BusinessException;

    PersonDTO update(UUID id, PersonDTO personDTO);

    PersonDTO merge(PersonDTO personDTO);
}
