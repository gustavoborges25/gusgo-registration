package gusgo.registration.application.interfaces;

import gusgo.registration.domain.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneRepository extends JpaRepository<Phone, UUID> {
}
