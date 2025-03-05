package gusgo.tdc.application.service;

import gusgo.tdc.application.dto.PersonDto;
import gusgo.tdc.application.factory.BusinessPersonFactory;
import gusgo.tdc.application.factory.IndividualPersonFactory;
import gusgo.tdc.application.interfaces.PersonFactory;
import gusgo.tdc.application.interfaces.PersonRepository;
import gusgo.tdc.application.interfaces.PersonService;
import gusgo.tdc.application.mapper.PersonMapper;
import gusgo.tdc.application.resources.PersonType;
import gusgo.tdc.application.resources.ValidationConstants;
import gusgo.tdc.domain.model.Person;
import gusgo.tdc.rest.exception.BusinessException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final Map<String, PersonFactory> factoryMap = new HashMap<>();

    public PersonServiceImpl(IndividualPersonFactory individualPersonFactory, BusinessPersonFactory businessPersonFactory, PersonRepository repository) {
        this.repository = repository;
        factoryMap.put(PersonType.INDIVIDUAL.getValue(), individualPersonFactory);
        factoryMap.put(PersonType.BUSINESS.getValue(), businessPersonFactory);
    }

    @Override
    public PersonDto create(PersonDto personDto) throws BusinessException {
        PersonFactory factory = factoryMap.get(personDto.getType().toUpperCase());

        if (factory == null) {
            throw new BusinessException(ValidationConstants.INVALID_PERSON_TYPE);
        }

        Person pessoa = factory.create(personDto);
        repository.save(pessoa);
        return personDto;
    }

    @Override
    public List<PersonDto> findAll() {
        List<Person> people = repository.findAll();
        return PersonMapper.INSTANCE.toDto(people);
    }

    @Override
    public void upload(MultipartFile file) throws BusinessException {
        if (file.isEmpty()) {
            throw new BusinessException(ValidationConstants.INVALID_FILE);
        }
        try (Workbook workbook = new HSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                String name = row.getCell(0).getStringCellValue();
                String document = row.getCell(1).getStringCellValue();

                PersonDto personDto = new PersonDto();
                personDto.setName(name);
                personDto.setMainDocument(document);
                personDto.setType("F");

                create(personDto);
            }
        } catch (IOException e) {
            throw new BusinessException(ValidationConstants.INVALID_FILE);
        }
    }
}
