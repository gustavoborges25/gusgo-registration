package gusgo.registration.unit;

import gusgo.registration.application.dto.EmailDTO;
import gusgo.registration.application.interfaces.EmailRepository;
import gusgo.registration.application.service.EmailServiceImpl;
import gusgo.registration.domain.model.Email;
import gusgo.registration.domain.model.BusinessPerson;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

    @Mock
    private EmailRepository repository;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Test
    void testUpdate_EmptyEmailList() {
        BusinessPerson person = new BusinessPerson();
        List<EmailDTO> emailDTOS = new ArrayList<>();

        BusinessException exception = assertThrows(
                BusinessException.class, () -> emailService.update(person, emailDTOS));

        assertEquals("At least one email is mandatory.", exception.getMessage());
    }

    @Test
    void testUpdate_WithNewEmail() {
        BusinessPerson person = new BusinessPerson();
        EmailDTO newEmailDTO = new EmailDTO();
        newEmailDTO.setEmail("new Email");
        newEmailDTO.setNote("new note");
        newEmailDTO.setContact("new contact");

        List<EmailDTO> emailDTOS = List.of(newEmailDTO);

        emailService.update(person, emailDTOS);

        assertEquals(1, person.getEmails().size());
        assertEquals("new Email", person.getEmails().getFirst().getEmail());
    }

    @Test
    void testUpdate_WithExistingEmail() {
        BusinessPerson person = new BusinessPerson();
        Email existingEmail = new Email();
        existingEmail.setId(UUID.fromString("5faab1b2-9efe-4d07-9da6-85b182825586"));
        existingEmail.setEmail("Old Email");
        person.setEmails(new ArrayList<>());
        person.addEmail(existingEmail);

        EmailDTO updatedEmailDTO = new EmailDTO();
        updatedEmailDTO.setId(UUID.fromString("5faab1b2-9efe-4d07-9da6-85b182825586"));
        updatedEmailDTO.setEmail("New Email");

        List<EmailDTO> emailDTOS = List.of(updatedEmailDTO);
        when(repository.findAll()).thenReturn(List.of(existingEmail));

        emailService.update(person, emailDTOS);

        assertEquals(1, person.getEmails().size());
        assertEquals("New Email", person.getEmails().getFirst().getEmail());
    }

    @Test
    void testUpdate_WithNonExistingEmail() {
        BusinessPerson person = new BusinessPerson();
        EmailDTO updatedEmailDTO = new EmailDTO();
        updatedEmailDTO.setId(UUID.randomUUID()); // Non-existent ID
        updatedEmailDTO.setEmail("Nonexistent Email");

        List<EmailDTO> emailDTOS = List.of(updatedEmailDTO);
        when(repository.findAll()).thenReturn(new ArrayList<>());

        emailService.update(person, emailDTOS);

        assertEquals(0, person.getEmails().size());
    }
}