package gusgo.registration.unit;

import gusgo.registration.application.dto.AddressDTO;
import gusgo.registration.application.dto.EmailDTO;
import gusgo.registration.application.dto.PersonDTO;
import gusgo.registration.application.dto.PhoneDTO;
import gusgo.registration.application.dto.SellerDTO;
import gusgo.registration.application.factory.IndividualPersonFactory;
import gusgo.registration.application.interfaces.AddressService;
import gusgo.registration.application.interfaces.EmailService;
import gusgo.registration.application.interfaces.IndividualPersonRepository;
import gusgo.registration.application.interfaces.PhoneService;
import gusgo.registration.application.interfaces.SellerService;
import gusgo.registration.domain.interfaces.IndividualPersonDomainService;
import gusgo.registration.domain.model.IndividualPerson;
import gusgo.registration.rest.exception.BusinessException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IndividualPersonFactoryTest {

    @Mock
    private IndividualPersonRepository repository;

    @Mock
    private IndividualPersonDomainService individualPersonDomainService;

    @Mock
    private AddressService addressService;

    @Mock
    private EmailService emailService;

    @Mock
    private PhoneService phoneService;

    @Mock
    private SellerService sellerService;

    @InjectMocks
    private IndividualPersonFactory individualPersonFactory;

    @Test
    void testCreate_Success() throws BusinessException {
        PersonDTO personDTO = getPersonDTO();

        IndividualPerson result = (IndividualPerson) individualPersonFactory.create(personDTO);

        assertNotNull(result);
        assertEquals("name", result.getName());
        assertEquals("email", result.getEmails().getFirst().getEmail());
        assertEquals("phone", result.getPhones().getFirst().getPhone());
        assertEquals("street", result.getAddresses().getFirst().getStreet());
        verify(individualPersonDomainService, times(1)).validateIndividualPerson(any());
        verify(repository, times(1)).save(any());
    }

    @Test
    void testCreate_ValidationError() throws BusinessException {
        PersonDTO personDTO = getPersonDTO();
        IndividualPerson individualPerson = new IndividualPerson();

        doThrow(new BusinessException("Validation error"))
                .when(individualPersonDomainService).validateIndividualPerson(any(IndividualPerson.class));

        BusinessException exception = assertThrows(
                BusinessException.class, () -> individualPersonFactory.create(personDTO));

        assertEquals("Validation error", exception.getMessage());
        verify(repository, never()).save(individualPerson);
    }

    @Test
    void testUpdate_Success() throws BusinessException {
        UUID id = UUID.randomUUID();
        PersonDTO personDTO = getPersonDTO();
        personDTO.setName("new Name");
        personDTO.setNickname("new Nickname");
        personDTO.setErpId("new ErpCode");
        personDTO.setMainDocument("new MainDocument");
        IndividualPerson existingIndividualPerson = new IndividualPerson();
        existingIndividualPerson.setName("old Name");
        existingIndividualPerson.setNickname("old Nickname");
        existingIndividualPerson.setErpId("old ErpCode");
        existingIndividualPerson.setDocumentCPF("old DocumentCPF");

        when(repository.findById(id)).thenReturn(Optional.of(existingIndividualPerson));
        doNothing().when(addressService).update(existingIndividualPerson, personDTO.getAddresses());
        doNothing().when(emailService).update(existingIndividualPerson, personDTO.getEmails());
        doNothing().when(phoneService).update(existingIndividualPerson, personDTO.getPhones());
        doNothing().when(individualPersonDomainService).validateIndividualPerson(existingIndividualPerson);
        when(repository.save(existingIndividualPerson)).thenReturn(existingIndividualPerson);

        IndividualPerson result = (IndividualPerson) individualPersonFactory.update(id, personDTO);

        assertEquals("new Name", result.getName());
        assertEquals("new Nickname", result.getNickname());
        assertEquals("new ErpCode", result.getErpId());
        assertEquals("new MainDocument", result.getDocumentCPF());
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(existingIndividualPerson);
    }

    @Test
    void testUpdate_PersonNotFound() {
        UUID id = UUID.randomUUID();
        PersonDTO personDTO = new PersonDTO();

        when(repository.findById(id)).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(
                BusinessException.class, () -> individualPersonFactory.update(id, personDTO));

        assertEquals("Person not found", exception.getMessage());
        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(any());
    }

    @Test
    void testUpdate_ValidationError() throws BusinessException {
        UUID id = UUID.randomUUID();
        PersonDTO personDTO = new PersonDTO();
        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setErpId("new ErpCode");
        personDTO.setSeller(sellerDTO);
        IndividualPerson existingIndividualPerson = new IndividualPerson();

        when(repository.findById(id)).thenReturn(Optional.of(existingIndividualPerson));
        doThrow(new BusinessException("Validation error"))
                .when(individualPersonDomainService).validateIndividualPerson(existingIndividualPerson);

        BusinessException exception = assertThrows(
                BusinessException.class, () -> individualPersonFactory.update(id, personDTO));

        assertEquals("Validation error", exception.getMessage());
        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(existingIndividualPerson);
    }

    private static @NotNull PersonDTO getPersonDTO() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName("name");
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail("email");
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet("street");
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setPhone("phone");

        SellerDTO seller = new SellerDTO();
        seller.setName("Seller");
        seller.setErpId("1234");

        personDTO.setEmails(List.of(emailDTO));
        personDTO.setPhones(List.of(phoneDTO));
        personDTO.setAddresses(List.of(addressDTO));
        personDTO.setSeller(seller);
        return personDTO;
    }
}