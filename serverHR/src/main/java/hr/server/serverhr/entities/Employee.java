package hr.server.serverhr.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int idEmployee;
    private String nom;
    private String prenom;
    private String mobile;
    @Enumerated(EnumType.STRING)
    private Fonction fonction ;
    private String email;

    private float soldecongé;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="employee")
    @JsonIgnore
    private Set<Présence> présences;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    @JsonIgnore
    private  Set<Conge> conges ;

}
