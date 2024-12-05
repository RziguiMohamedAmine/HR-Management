package hr.server.serverhr.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long idNotif;
    private Long adminId;
    private String message;
    private LocalDateTime timestamp;
    public Notification(String message, Long adminId) {
        this.message = message;
        this.adminId = adminId;
        this.timestamp = LocalDateTime.now();
    }

    public Notification(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
