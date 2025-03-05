package gusgo.tdc.application.interfaces;

import gusgo.tdc.application.dto.PersonDto;
import gusgo.tdc.rest.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PersonService {

    PersonDto create(PersonDto personDto) throws BusinessException;

    List<PersonDto> findAll();

    void upload(MultipartFile file) throws BusinessException;
}
