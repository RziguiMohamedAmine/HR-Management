package hr.server.serverhr.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Présence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;


    private LocalDateTime arrivalTime;

    private LocalDateTime departureTime;

    @Temporal(TemporalType.DATE)
    private Date day;
    @ManyToOne
    Employee employee;

}
