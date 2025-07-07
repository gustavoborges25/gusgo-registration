package gusgo.person.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "juridical_person")
@Getter
@Setter
public class JuridicalPerson extends Person {

    @Column(name = "document_cnpj")
    private String documentCNPJ;

    @Column(name = "document_ie")
    private String documentIE;

    @Column(name = "is_branch")
    private Boolean isBranch;
}
