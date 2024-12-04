package hr.server.serverhr.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Allocation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "mat_id") // Foreign key referencing Mat
    private Materials material;

    @ManyToOne
    @JoinColumn(name = "employee_id") // Foreign key referencing Employee (you can change the type to your Employee entity)
    private Employee employee;

    private int quantityAllocated; // The quantity of the material allocated

    private LocalDateTime allocationDate;
}
