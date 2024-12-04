package hr.server.serverhr.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private String image;
    private String addresse;

    private float soldecongé;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="employee")
    @JsonIgnore
    private Set<Présence> présences;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    @JsonIgnore
    private  Set<Conge> conges ;

    @ManyToOne
    Projet projet;

    @OneToOne(mappedBy = "teamManager")
    private Projet managedProject;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    @JsonIgnore
    private Set<Allocation> allocations ;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Salary salary;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    @JsonIgnore
    private  Set<PaymentHistory> paymentHistories ;

    @ManyToMany(mappedBy = "attendees")
    private List<Training> trainingClasses;
}
