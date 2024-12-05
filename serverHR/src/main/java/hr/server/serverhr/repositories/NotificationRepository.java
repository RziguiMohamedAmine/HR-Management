package hr.server.serverhr.repositories;

import hr.server.serverhr.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
