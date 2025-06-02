package gusgo.person.unit;

import gusgo.person.application.dto.PersonDTO;
import gusgo.person.application.factory.BusinessPersonFactory;
import gusgo.person.application.factory.IndividualPersonFactory;
import gusgo.person.application.interfaces.BusinessPersonRepository;
import gusgo.person.application.interfaces.IndividualPersonRepository;
import gusgo.person.application.service.PersonServiceImpl;
import gusgo.person.domain.model.BusinessPerson;
import gusgo.person.domain.model.IndividualPerson;
import gusgo.person.rest.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private BusinessPersonFactory businessPersonFactory;

    @Mock
    private BusinessPersonRepository businessPersonRepository;

    @Mock
    private IndividualPersonRepository individualPersonRepository;

    @Mock
    private IndividualPersonFactory individualPersonFactory;

    @InjectMocks
    private PersonServiceImpl personService;



    @Test
    void testCreate_ValidPersonType() throws BusinessException {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setType("BUSINESS");

        BusinessPerson businessPerson = new BusinessPerson();

        when(businessPersonFactory.create(personDTO)).thenReturn(businessPerson);

        PersonDTO result = personService.create(personDTO);

        assertEquals(personDTO, result);
        verify(businessPersonFactory, times(1)).create(personDTO);
    }

    @Test
    void testCreate_InvalidPersonType() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setType("INVALID_TYPE");

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            personService.create(personDTO);
        });

        assertEquals("INVALID_PERSON_TYPE", exception.getMessage());
    }

    @Test
    void testFindAll() {
        when(businessPersonRepository.findAll()).thenReturn(Collections.emptyList());
        when(individualPersonRepository.findAll()).thenReturn(Collections.emptyList());

        List<PersonDTO> result = personService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testUploadBp_ValidFile() throws Exception {
        MultipartFile file = mock(MultipartFile.class);
        when(file.isEmpty()).thenReturn(false);
        when(file.getInputStream()).thenThrow(new IOException());

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            personService.uploadBp(file);
        });

        assertEquals("INVALID_FILE", exception.getMessage());
    }

    @Test
    void testUpdate_ValidPersonType() throws BusinessException {
        UUID id = UUID.randomUUID();
        PersonDTO personDTO = new PersonDTO();
        personDTO.setType("INDIVIDUAL");

        IndividualPerson individualPerson = new IndividualPerson();

        when(individualPersonFactory.update(id, personDTO)).thenReturn(individualPerson);

        PersonDTO result = personService.update(id, personDTO);

        assertEquals(personDTO, result);
        verify(individualPersonFactory, times(1)).update(id, personDTO);
    }

    @Test
    void testUpdate_InvalidPersonType() {
        UUID id = UUID.randomUUID();
        PersonDTO personDTO = new PersonDTO();
        personDTO.setType("INVALID_TYPE");

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            personService.update(id, personDTO);
        });

        assertEquals("INVALID_PERSON_TYPE", exception.getMessage());
    }

}
