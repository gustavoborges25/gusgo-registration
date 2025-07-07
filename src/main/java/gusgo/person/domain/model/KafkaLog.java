package gusgo.person.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "kafka_log")
@Getter
@Setter
public class KafkaLog {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "consumer")
    private String consumer;

    @Column(name = "message")
    private String message;

    @Column(name = "exception")
    private String exception;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public KafkaLog(String consumer, String message, String exception) {
        this.consumer = consumer;
        this.message = message;
        this.exception = exception;
    }
}
