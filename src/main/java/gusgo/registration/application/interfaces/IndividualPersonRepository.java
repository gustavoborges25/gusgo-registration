package gusgo.registration.application.interfaces;

import gusgo.registration.domain.model.IndividualPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IndividualPersonRepository extends JpaRepository<IndividualPerson, UUID> {

    Optional<IndividualPerson> findByDocumentCPF(String documentCPF);

}
