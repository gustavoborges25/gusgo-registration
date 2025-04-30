package gusgo.person.application.interfaces;

import gusgo.person.domain.model.IndividualPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IndividualPersonRepository extends JpaRepository<IndividualPerson, UUID> {
}
