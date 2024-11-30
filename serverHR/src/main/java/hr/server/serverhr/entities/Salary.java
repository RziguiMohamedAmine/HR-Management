package hr.server.serverhr.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Salary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double baseSalary;
    private double bonuses;
    @Column(nullable = false)
    private FrequencyPay paymentFrequency;
    private double deductions;
    @OneToOne
    @JoinColumn(name = "idEmployee", nullable = false)
    private Employee employee;


}
