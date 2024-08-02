package hr.server.serverhr.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;


public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int idEmployee;
    private String nom;
    private String prenom;
    private String mobile;
    @Enumerated(EnumType.STRING)
    private Role role ;
    @Temporal(TemporalType.DATE)
    private Date date_entrée;
    @Temporal(TemporalType.DATE)
    private Date date_sortie;
    private String email;


}
