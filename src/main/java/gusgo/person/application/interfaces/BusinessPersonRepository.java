package gusgo.person.application.interfaces;

import gusgo.person.domain.model.BusinessPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BusinessPersonRepository extends JpaRepository<BusinessPerson, UUID> {

}
