package gusgo.registration.unit;

import gusgo.registration.application.dto.PersonDTO;
import gusgo.registration.application.factory.BusinessPersonFactory;
import gusgo.registration.application.factory.IndividualPersonFactory;
import gusgo.registration.application.interfaces.BusinessPersonRepository;
import gusgo.registration.application.interfaces.IndividualPersonRepository;
import gusgo.registration.application.service.PersonServiceImpl;
import gusgo.registration.domain.model.BusinessPerson;
import gusgo.registration.domain.model.IndividualPerson;
import gusgo.registration.rest.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void testCreate_InvalidPersonType() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setType("INVALID_TYPE");

        BusinessException exception = assertThrows(
                BusinessException.class, () -> personService.create(personDTO));

        assertEquals("Invalid person type", exception.getMessage());
    }

    @Test
    void testCreate_BusinessPersonType() throws BusinessException {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setType("BUSINESS");

        PersonDTO result = personService.create(personDTO);

        assertEquals(personDTO, result);
        verify(businessPersonFactory, times(1)).create(personDTO);
        verify(individualPersonFactory, times(0)).create(personDTO);
    }

    @Test
    void testCreate_IndividualPersonType() throws BusinessException {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setType("INDIVIDUAL");

        PersonDTO result = personService.create(personDTO);

        assertEquals(personDTO, result);
        verify(businessPersonFactory, times(0)).create(personDTO);
        verify(individualPersonFactory, times(1)).create(personDTO);
    }

    @Test
    void testFindAll() {
        List<BusinessPerson> businessPeople = new ArrayList<>();
        BusinessPerson businessPerson = new BusinessPerson();
        businessPerson.setName("Company test");
        businessPeople.add(businessPerson);

        List<IndividualPerson> individualPeople = new ArrayList<>();
        IndividualPerson individualPerson = new IndividualPerson();
        individualPerson.setName("Individual test");
        individualPeople.add(individualPerson);

        when(businessPersonRepository.findAll()).thenReturn(businessPeople);
        when(individualPersonRepository.findAll()).thenReturn(individualPeople);

        List<PersonDTO> result = personService.findAll();

        assertFalse(result.isEmpty());
        assertEquals("Company test", result.getFirst().getName());
        assertEquals("Individual test", result.get(1).getName());
    }

    @Test
    void testUpdate_InvalidPersonType() {
        UUID id = UUID.randomUUID();
        PersonDTO personDTO = new PersonDTO();
        personDTO.setType("INVALID_TYPE");

        BusinessException exception = assertThrows(
                BusinessException.class, () -> personService.update(id, personDTO));

        assertEquals("Invalid person type", exception.getMessage());
    }


    @Test
    void testUpdate_BusinessPersonType() throws BusinessException {
        UUID id = UUID.randomUUID();
        PersonDTO personDTO = new PersonDTO();
        personDTO.setType("BUSINESS");

        PersonDTO result = personService.update(id, personDTO);

        assertEquals(personDTO, result);
        verify(businessPersonFactory, times(1)).update(id, personDTO);
        verify(individualPersonFactory, times(0)).update(id, personDTO);
    }

    @Test
    void testUpdate_IndividualPersonType() throws BusinessException {
        UUID id = UUID.randomUUID();
        PersonDTO personDTO = new PersonDTO();
        personDTO.setType("INDIVIDUAL");

        PersonDTO result = personService.update(id, personDTO);

        assertEquals(personDTO, result);
        verify(businessPersonFactory, times(0)).update(id, personDTO);
        verify(individualPersonFactory, times(1)).update(id, personDTO);
    }
}
