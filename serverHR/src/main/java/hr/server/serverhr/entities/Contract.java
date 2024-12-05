package hr.server.serverhr.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private Date startDate;
    private Date endDate;
    private String terms;

    public Contract(Employee employee, Date startDate, Date endDate, String terms) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.terms = terms;
    }


}
