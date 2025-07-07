package gusgo.person.application.interfaces;

import gusgo.person.domain.model.NaturalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NaturalPersonRepository extends JpaRepository<NaturalPerson, UUID> {

    Optional<NaturalPerson> findByDocumentCPF(String documentCPF);

    Optional<NaturalPerson> findByErpId(String erpId);
}
