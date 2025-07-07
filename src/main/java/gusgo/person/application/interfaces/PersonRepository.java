package gusgo.person.application.interfaces;

import gusgo.person.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    Optional<Person> findByErpId(String erpId);

}
