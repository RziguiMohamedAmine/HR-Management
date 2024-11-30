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
    private String totalTimeWorked;

    @Temporal(TemporalType.DATE)
    private Date day;

    @ManyToOne
    Employee employee;


    public double getHoursWorkedAsDecimal() {
        String[] timeParts = totalTimeWorked.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        return hours + (minutes / 60.0);
    }

    // Calculate extra hours (hours worked over 8)
    public double getExtraHours() {
        double hoursWorked = getHoursWorkedAsDecimal();
        return Math.max(0, hoursWorked - 8);
    }

    // Calculate the bonus for extra hours
    public double calculateBonusForExtraHours(double hourlyBonusRate) {
        return getExtraHours() * hourlyBonusRate;
    }

}
