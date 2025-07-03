package gusgo.registration.application.interfaces;

import gusgo.registration.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    Optional<Person> findByErpId(String erpId);

}
