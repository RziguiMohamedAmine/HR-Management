package hr.server.serverhr.repositories;

import hr.server.serverhr.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Projet,Integer> {
}
