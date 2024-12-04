package hr.server.serverhr.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Materials implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long idMat;
    private String name;
    private String image;
    private int quantity;
    private MatStatus status;
    private double cost;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    @JsonIgnore
    private Set<Allocation> allocations ;

    public void decreaseQuantity(int quantityAllocated) {
        if (this.quantity >= quantityAllocated) {
            this.quantity -= quantityAllocated;
        } else {
            throw new IllegalStateException("Not enough material available to allocate");
        }
    }
}
