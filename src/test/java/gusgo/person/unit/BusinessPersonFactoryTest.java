package gusgo.person.unit;

import gusgo.person.application.dto.*;
import gusgo.person.application.factory.JuridicalPersonFactory;
import gusgo.person.application.interfaces.*;
import gusgo.person.domain.interfaces.JuridicalPersonDomainService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class BusinessPersonFactoryTest {

    @Mock
    private JuridicalPersonRepository repository;

    @Mock
    private JuridicalPersonDomainService businessPersonDomainService;

    @Mock
    private AddressService addressService;

    @Mock
    private EmailService emailService;

    @Mock
    private PhoneService phoneService;

    @Mock
    private SellerService sellerService;

    @InjectMocks
    private JuridicalPersonFactory businessPersonFactory;

//    @Test
//    void testCreate_Success() throws BusinessException {
//        PersonDTO personDTO = getPersonDTO();
//
//        Seller seller = new Seller();
//        seller.setName("Seller");
//
//        when(sellerService.findByErpId(any())).thenReturn(seller);
//
//        JuridicalPerson result = (JuridicalPerson) businessPersonFactory.create(personDTO);
//
//        assertNotNull(result);
//        assertEquals("name", result.getName());
//        assertEquals("email", result.getEmails().getFirst().getEmail());
//        assertEquals("phone", result.getPhones().getFirst().getPhone());
//        assertEquals("street", result.getAddresses().getFirst().getStreet());
//        verify(businessPersonDomainService, times(1)).validateBusinessPerson(any());
//        verify(repository, times(1)).save(any());
//    }

//    @Test
//    void testCreate_ValidationError() throws BusinessException {
//        PersonDTO personDTO = getPersonDTO();
//        JuridicalPerson businessPerson = new JuridicalPerson();
//
//        doThrow(new BusinessException("Validation error"))
//                .when(businessPersonDomainService).validateBusinessPerson(any(JuridicalPerson.class));
//
//        BusinessException exception = assertThrows(
//                BusinessException.class, () -> businessPersonFactory.create(personDTO));
//
//        assertEquals("Validation error", exception.getMessage());
//        verify(repository, never()).save(businessPerson);
//    }

//    @Test
//    void testUpdate_Success() throws BusinessException {
//        UUID id = UUID.randomUUID();
//        PersonDTO personDTO = getPersonDTO();
//        personDTO.setName("new Name");
//        personDTO.setNickname("new Nickname");
//        personDTO.setErpId("new ErpCode");
//        personDTO.setMainDocument("new MainDocument");
//        JuridicalPerson existingBusinessPerson = new JuridicalPerson();
//        existingBusinessPerson.setName("old Name");
//        existingBusinessPerson.setNickname("old Nickname");
//        existingBusinessPerson.setErpId("old ErpCode");
//        existingBusinessPerson.setDocumentCNPJ("old DocumentCNPJ");
//
//        when(repository.findById(id)).thenReturn(Optional.of(existingBusinessPerson));
//        doNothing().when(addressService).update(existingBusinessPerson, personDTO.getAddresses());
//        doNothing().when(emailService).update(existingBusinessPerson, personDTO.getEmails());
//        doNothing().when(phoneService).update(existingBusinessPerson, personDTO.getPhones());
//        doNothing().when(businessPersonDomainService).validateBusinessPerson(existingBusinessPerson);
//        when(repository.save(existingBusinessPerson)).thenReturn(existingBusinessPerson);
//
//        JuridicalPerson result = (JuridicalPerson) businessPersonFactory.update(id, personDTO);
//
//        assertEquals("new Name", result.getName());
//        assertEquals("new Nickname", result.getNickname());
//        assertEquals("new ErpCode", result.getErpId());
//        assertEquals("new MainDocument", result.getDocumentCNPJ());
//        verify(repository, times(1)).findById(id);
//        verify(repository, times(1)).save(existingBusinessPerson);
//    }

//    @Test
//    void testUpdate_PersonNotFound() {
//        UUID id = UUID.randomUUID();
//        PersonDTO personDTO = new PersonDTO();
//
//        when(repository.findById(id)).thenReturn(Optional.empty());
//
//        BusinessException exception = assertThrows(
//                BusinessException.class, () -> businessPersonFactory.update(id, personDTO));
//
//        assertEquals("Person not found", exception.getMessage());
//        verify(repository, times(1)).findById(id);
//        verify(repository, never()).save(any());
//    }

//    @Test
//    void testUpdate_ValidationError() throws BusinessException {
//        UUID id = UUID.randomUUID();
//        SellerDTO sellerDTO = new SellerDTO();
//        sellerDTO.setErpId("new ErpCode");
//        PersonDTO personDTO = new PersonDTO();
//        personDTO.setSeller(sellerDTO);
//        JuridicalPerson existingBusinessPerson = new JuridicalPerson();
//
//        Seller seller = new Seller();
//        seller.setName("Seller");
//
//        when(sellerService.findByErpId(any())).thenReturn(seller);
//        when(repository.findById(id)).thenReturn(Optional.of(existingBusinessPerson));
//        doThrow(new BusinessException("Validation error"))
//                .when(businessPersonDomainService).validateBusinessPerson(existingBusinessPerson);
//
//        BusinessException exception = assertThrows(
//                BusinessException.class, () -> businessPersonFactory.update(id, personDTO));
//
//        assertEquals("Validation error", exception.getMessage());
//        verify(repository, times(1)).findById(id);
//        verify(repository, never()).save(existingBusinessPerson);
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