package gusgo.person.unit;

import gusgo.person.application.dto.PersonDTO;
import gusgo.person.application.factory.JuridicalPersonFactory;
import gusgo.person.application.factory.NaturalPersonFactory;
import gusgo.person.application.interfaces.JuridicalPersonRepository;
import gusgo.person.application.interfaces.NaturalPersonRepository;
import gusgo.person.application.service.PersonServiceImpl;
import gusgo.person.rest.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private JuridicalPersonFactory businessPersonFactory;

    @Mock
    private JuridicalPersonRepository businessPersonRepository;

    @Mock
    private NaturalPersonRepository individualPersonRepository;

    @Mock
    private NaturalPersonFactory individualPersonFactory;

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

//    @Test
//    void testCreate_BusinessPersonType() throws BusinessException {
//        PersonDTO personDTO = new PersonDTO();
//        personDTO.setType("BUSINESS");
//
//        PersonDTO result = personService.create(personDTO);
//
//        assertEquals(personDTO, result);
//        verify(businessPersonFactory, times(1)).create(personDTO);
//        verify(individualPersonFactory, times(0)).create(personDTO);
//    }

//    @Test
//    void testCreate_IndividualPersonType() throws BusinessException {
//        PersonDTO personDTO = new PersonDTO();
//        personDTO.setType("INDIVIDUAL");
//
//        PersonDTO result = personService.create(personDTO);
//
//        assertEquals(personDTO, result);
//        verify(businessPersonFactory, times(0)).create(personDTO);
//        verify(individualPersonFactory, times(1)).create(personDTO);
//    }
//
//    @Test
//    void testFindAll() {
//        List<JuridicalPerson> businessPeople = new ArrayList<>();
//        JuridicalPerson businessPerson = new JuridicalPerson();
//        businessPerson.setName("Company test");
//        businessPeople.add(businessPerson);
//
//        List<NaturalPerson> individualPeople = new ArrayList<>();
//        NaturalPerson individualPerson = new NaturalPerson();
//        individualPerson.setName("Individual test");
//        individualPeople.add(individualPerson);
//
//        when(businessPersonRepository.findAll()).thenReturn(businessPeople);
//        when(individualPersonRepository.findAll()).thenReturn(individualPeople);
//
//        List<PersonDTO> result = personService.findAll();
//
//        assertFalse(result.isEmpty());
//        assertEquals("Company test", result.getFirst().getName());
//        assertEquals("Individual test", result.get(1).getName());
//    }
//
//    @Test
//    void testUpdate_InvalidPersonType() {
//        UUID id = UUID.randomUUID();
//        PersonDTO personDTO = new PersonDTO();
//        personDTO.setType("INVALID_TYPE");
//
//        BusinessException exception = assertThrows(
//                BusinessException.class, () -> personService.update(id, personDTO));
//
//        assertEquals("Invalid person type", exception.getMessage());
//    }
//
//
//    @Test
//    void testUpdate_BusinessPersonType() throws BusinessException {
//        UUID id = UUID.randomUUID();
//        PersonDTO personDTO = new PersonDTO();
//        personDTO.setType("BUSINESS");
//
//        PersonDTO result = personService.update(id, personDTO);
//
//        assertEquals(personDTO, result);
//        verify(businessPersonFactory, times(1)).update(id, personDTO);
//        verify(individualPersonFactory, times(0)).update(id, personDTO);
//    }
//
//    @Test
//    void testUpdate_IndividualPersonType() throws BusinessException {
//        UUID id = UUID.randomUUID();
//        PersonDTO personDTO = new PersonDTO();
//        personDTO.setType("INDIVIDUAL");
//
//        PersonDTO result = personService.update(id, personDTO);
//
//        assertEquals(personDTO, result);
//        verify(businessPersonFactory, times(0)).update(id, personDTO);
//        verify(individualPersonFactory, times(1)).update(id, personDTO);
//    }
}
