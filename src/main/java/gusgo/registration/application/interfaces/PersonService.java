package gusgo.registration.application.interfaces;

import gusgo.registration.application.dto.PersonDTO;
import gusgo.registration.rest.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    PersonDTO create(PersonDTO personDto) throws BusinessException;

    List<PersonDTO> findAll();

    PersonDTO update(UUID id, PersonDTO personDTO);

    void merge(PersonDTO personDTO);
}
