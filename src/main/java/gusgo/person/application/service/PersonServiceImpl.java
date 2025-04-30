package gusgo.person.application.service;

import gusgo.person.application.dto.AddressDTO;
import gusgo.person.application.dto.EmailDTO;
import gusgo.person.application.dto.PersonDTO;
import gusgo.person.application.dto.PhoneDTO;
import gusgo.person.application.factory.BusinessPersonFactory;
import gusgo.person.application.factory.IndividualPersonFactory;
import gusgo.person.application.interfaces.IndividualPersonRepository;
import gusgo.person.application.interfaces.PersonFactory;
import gusgo.person.application.interfaces.BusinessPersonRepository;
import gusgo.person.application.interfaces.PersonService;
import gusgo.person.application.mapper.PersonMapper;
import gusgo.person.application.resources.PersonType;
import gusgo.person.application.resources.ValidationConstants;
import gusgo.person.domain.model.BusinessPerson;
import gusgo.person.domain.model.IndividualPerson;
import gusgo.person.rest.exception.BusinessException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

    private final BusinessPersonRepository businessPersonRepository;
    private final IndividualPersonRepository individualPersonRepository;
    private final Map<String, PersonFactory> factoryMap = new HashMap<>();

    public PersonServiceImpl(IndividualPersonFactory individualPersonFactory, BusinessPersonFactory businessPersonFactory,
                             BusinessPersonRepository businessPersonRepository, IndividualPersonRepository individualPersonRepository) {
        this.businessPersonRepository = businessPersonRepository;
        this.individualPersonRepository = individualPersonRepository;
        factoryMap.put(PersonType.INDIVIDUAL.getValue(), individualPersonFactory);
        factoryMap.put(PersonType.BUSINESS.getValue(), businessPersonFactory);
    }

    @Override
    public PersonDTO create(PersonDTO personDTO) throws BusinessException {
        PersonFactory factory = factoryMap.get(personDTO.getType().toUpperCase());

        if (factory == null) {
            throw new BusinessException(ValidationConstants.INVALID_PERSON_TYPE);
        }

        factory.create(personDTO);

        return personDTO;
    }

    @Override
    public List<PersonDTO> findAll() {
        List<PersonDTO> people = new ArrayList<>();

        List<BusinessPerson> businessPeople = businessPersonRepository.findAll();
        List<IndividualPerson> individualPeople = individualPersonRepository.findAll();

        List<PersonDTO> businessPersonDTOs = PersonMapper.INSTANCE.businessPersonToDTO(businessPeople);
        List<PersonDTO> individualPersonDTOs = PersonMapper.INSTANCE.individualPersonToDTO(individualPeople);

        people.addAll(businessPersonDTOs);
        people.addAll(individualPersonDTOs);

        return people;
    }

    @Override
    public void uploadBp(MultipartFile file) throws BusinessException {
        if (file.isEmpty()) {
            throw new BusinessException(ValidationConstants.INVALID_FILE);
        }
        try (Workbook workbook = new HSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() > 4) {
                    String name = row.getCell(1).getStringCellValue();
                    String nickname = row.getCell(2).getStringCellValue();
                    String mainDocument = row.getCell(14).getStringCellValue();
                    String secondaryDocument = row.getCell(15).getStringCellValue();

                    PersonDTO personDto = new PersonDTO();
                    personDto.setName(name);
                    personDto.setNickname(nickname);
                    personDto.setMainDocument(mainDocument);
                    personDto.setSecondaryDocument(secondaryDocument);
                    personDto.setType(PersonType.BUSINESS.getValue());

                    String street = row.getCell(3).getStringCellValue();
                    String number = row.getCell(4).getStringCellValue();
                    String neighborhood = row.getCell(5).getStringCellValue();
                    String city = row.getCell(6).getStringCellValue();
                    String state = row.getCell(7).getStringCellValue();
                    String zipCode = row.getCell(8).getStringCellValue();

                    AddressDTO addressDto = new AddressDTO();
                    addressDto.setStreet(street);
                    addressDto.setNumber(number);
                    addressDto.setNeighborhood(neighborhood);
                    addressDto.setCity(city);
                    addressDto.setState(state);
                    addressDto.setZipCode(zipCode);

                    personDto.setAddresses(List.of(addressDto));

                    String phone = row.getCell(9).getStringCellValue();

                    List<PhoneDTO> phonesDto = new ArrayList<>();

                    PhoneDTO mainPhoneDTO = new PhoneDTO();
                    mainPhoneDTO.setPhone(phone);
                    mainPhoneDTO.setNote("Company phone");

                    phonesDto.add(mainPhoneDTO);

                    String sellerPhone = row.getCell(19).getStringCellValue();
                    String sellerName = row.getCell(18).getStringCellValue();

                    PhoneDTO sellerPhoneDTO = new PhoneDTO();
                    sellerPhoneDTO.setPhone(sellerPhone);
                    sellerPhoneDTO.setContact(sellerName);
                    sellerPhoneDTO.setNote("Seller phone");

                    phonesDto.add(sellerPhoneDTO);

                    personDto.setPhones(phonesDto);

                    String email = row.getCell(11).getStringCellValue();

                    EmailDTO emailDto = new EmailDTO();
                    emailDto.setEmail(email);
                    emailDto.setNote("Company mail");

                    personDto.setEmails(List.of(emailDto));

                    create(personDto);
                }
            }
        } catch (IOException e) {
            throw new BusinessException(ValidationConstants.INVALID_FILE);
        }
    }

    @Override
    public PersonDTO update(UUID id, PersonDTO personDTO) {
        PersonFactory factory = factoryMap.get(personDTO.getType().toUpperCase());

        if (factory == null) {
            throw new BusinessException(ValidationConstants.INVALID_PERSON_TYPE);
        }

        factory.uodate(id, personDTO);

        return personDTO;
    }
}
