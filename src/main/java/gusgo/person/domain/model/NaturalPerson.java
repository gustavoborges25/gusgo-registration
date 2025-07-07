package gusgo.person.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "natural_person")
@Getter
@Setter
public class NaturalPerson extends Person {

    @Column(name = "document_cpf")
    private String documentCPF;

    @Column(name = "document_rg")
    private String documentRG;

}
