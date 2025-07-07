package gusgo.person.application.interfaces;

import gusgo.person.domain.model.JuridicalPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JuridicalPersonRepository extends JpaRepository<JuridicalPerson, UUID> {

    Optional<JuridicalPerson> findByDocumentCNPJ(String documentCNPJ);

    Optional<JuridicalPerson> findByErpId(String erpId);
}
