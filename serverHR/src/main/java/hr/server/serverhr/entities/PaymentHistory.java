package hr.server.serverhr.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private double baseSalary;
    private double bonuses;
    private double deductions;
    private double totalPaid; // Final amount paid after bonuses and deductions
    private LocalDate paymentDate; // Date when the payment was validated

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

}
