package gusgo.person.application.interfaces;

import gusgo.person.domain.model.Person;
import gusgo.person.domain.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneRepository extends JpaRepository<Phone, UUID> {
    void deleteAllByPerson(Person person);
}
