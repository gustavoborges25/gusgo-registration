package gusgo.registration.application.interfaces;

import gusgo.registration.domain.model.BusinessPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BusinessPersonRepository extends JpaRepository<BusinessPerson, UUID> {

    Optional<BusinessPerson> findByDocumentCNPJ(String documentCNPJ);

}
