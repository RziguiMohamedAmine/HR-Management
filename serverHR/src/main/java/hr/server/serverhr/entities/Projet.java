package hr.server.serverhr.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Projet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int idProjet;
    private String projectName;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date dateDebutProjet;
    @Temporal(TemporalType.DATE)
    private Date deadLine;
    Status status;
    Priority priority;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projet")
    @JsonIgnore
    private Set<Employee> employees ;

    @ElementCollection
    @CollectionTable(name = "project_attachments", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "attachment")
    private List<String> attachments;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @OneToOne
    @JoinColumn(name = "team_manager_id", unique = true)
    Employee teamManager;
}
