package gusgo.person.application.interfaces;

import gusgo.person.domain.model.Email;
import gusgo.person.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
    void deleteAllByPerson(Person person);
}
