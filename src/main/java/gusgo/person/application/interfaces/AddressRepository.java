package gusgo.person.application.interfaces;

import gusgo.person.domain.model.Address;
import gusgo.person.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    void deleteAllByPerson(Person person);
}
