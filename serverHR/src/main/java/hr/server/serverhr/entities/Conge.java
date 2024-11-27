package hr.server.serverhr.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Conge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date dateDebutConge;
    @Temporal(TemporalType.DATE)
    private Date dateFinConge;

    private boolean validé;

    @ManyToOne
    Employee employee;

}
