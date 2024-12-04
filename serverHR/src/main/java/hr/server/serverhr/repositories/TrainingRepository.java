package hr.server.serverhr.repositories;

import hr.server.serverhr.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    Optional<Training> findTrainingByName(String name);
}
