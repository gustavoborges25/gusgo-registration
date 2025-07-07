package gusgo.person.unit;

import gusgo.person.application.dto.*;
import gusgo.person.application.factory.NaturalPersonFactory;
import gusgo.person.application.interfaces.*;
import gusgo.person.domain.interfaces.NaturalPersonDomainService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class IndividualPersonFactoryTest {

    @Mock
    private NaturalPersonRepository repository;

    @Mock
    private NaturalPersonDomainService individualPersonDomainService;

    @Mock
    private AddressService addressService;

    @Mock
    private EmailService emailService;

    @Mock
    private PhoneService phoneService;

    @Mock
    private SellerService sellerService;

    @InjectMocks
    private NaturalPersonFactory individualPersonFactory;

//    @Test
//    void testCreate_Success() throws BusinessException {
//        PersonDTO personDTO = getPersonDTO();
//
//        NaturalPerson result = (NaturalPerson) individualPersonFactory.create(personDTO);
//
//        assertNotNull(result);
//        assertEquals("name", result.getName());
//        verify(individualPersonDomainService, times(1)).validateNaturalPerson(any());
//        verify(repository, times(1)).save(any());
//    }
//
//    @Test
//    void testCreate_ValidationError() throws BusinessException {
//        PersonDTO personDTO = getPersonDTO();
//        NaturalPerson individualPerson = new NaturalPerson();
//
//        doThrow(new BusinessException("Validation error"))
//                .when(individualPersonDomainService).validateNaturalPerson(any(NaturalPerson.class));
//
//        BusinessException exception = assertThrows(
//                BusinessException.class, () -> individualPersonFactory.create(personDTO));
//
//        assertEquals("Validation error", exception.getMessage());
//        verify(repository, never()).save(individualPerson);
//    }
//
//    @Test
//    void testUpdate_Success() throws BusinessException {
//        UUID id = UUID.randomUUID();
//        PersonDTO personDTO = getPersonDTO();
//        personDTO.setName("new Name");
//        personDTO.setNickname("new Nickname");
//        personDTO.setErpId("new ErpCode");
//        personDTO.setMainDocument("new MainDocument");
//        NaturalPerson existingIndividualPerson = new NaturalPerson();
//        existingIndividualPerson.setName("old Name");
//        existingIndividualPerson.setNickname("old Nickname");
//        existingIndividualPerson.setErpId("old ErpCode");
//        existingIndividualPerson.setDocumentCPF("old DocumentCPF");
//
//        when(repository.findById(id)).thenReturn(Optional.of(existingIndividualPerson));
//        doNothing().when(addressService).update(existingIndividualPerson, personDTO.getAddresses());
//        doNothing().when(emailService).update(existingIndividualPerson, personDTO.getEmails());
//        doNothing().when(phoneService).update(existingIndividualPerson, personDTO.getPhones());
//        doNothing().when(individualPersonDomainService).validateNaturalPerson(existingIndividualPerson);
//        when(repository.save(existingIndividualPerson)).thenReturn(existingIndividualPerson);
//
//        NaturalPerson result = (NaturalPerson) individualPersonFactory.update(id, personDTO);
//
//        assertEquals("new Name", result.getName());
//        assertEquals("new Nickname", result.getNickname());
//        assertEquals("new ErpCode", result.getErpId());
//        assertEquals("new MainDocument", result.getDocumentCPF());
//        verify(repository, times(1)).findById(id);
//        verify(repository, times(1)).save(existingIndividualPerson);
//    }
//
//    @Test
//    void testUpdate_PersonNotFound() {
//        UUID id = UUID.randomUUID();
//        PersonDTO personDTO = new PersonDTO();
//
//        when(repository.findById(id)).thenReturn(Optional.empty());
//
//        BusinessException exception = assertThrows(
//                BusinessException.class, () -> individualPersonFactory.update(id, personDTO));
//
//        assertEquals("Person not found", exception.getMessage());
//        verify(repository, times(1)).findById(id);
//        verify(repository, never()).save(any());
//    }
//
//    @Test
//    void testUpdate_ValidationError() throws BusinessException {
//        UUID id = UUID.randomUUID();
//        PersonDTO personDTO = new PersonDTO();
//        SellerDTO sellerDTO = new SellerDTO();
//        sellerDTO.setErpId("new ErpCode");
//        personDTO.setSeller(sellerDTO);
//        NaturalPerson existingIndividualPerson = new NaturalPerson();
//
//        when(repository.findById(id)).thenReturn(Optional.of(existingIndividualPerson));
//        doThrow(new BusinessException("Validation error"))
//                .when(individualPersonDomainService).validateNaturalPerson(existingIndividualPerson);
//
//        BusinessException exception = assertThrows(
//                BusinessException.class, () -> individualPersonFactory.update(id, personDTO));
//
//        assertEquals("Validation error", exception.getMessage());
//        verify(repository, times(1)).findById(id);
//        verify(repository, never()).save(existingIndividualPerson);
//    }

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