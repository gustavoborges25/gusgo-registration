package gusgo.person.application.interfaces;

import gusgo.person.domain.model.KafkaLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KafkaLogRepository extends JpaRepository<KafkaLog, UUID> {

}
