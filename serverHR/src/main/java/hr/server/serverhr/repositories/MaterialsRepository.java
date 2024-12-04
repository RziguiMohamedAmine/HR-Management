package hr.server.serverhr.repositories;

import hr.server.serverhr.entities.Materials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialsRepository extends JpaRepository<Materials,Long> {

}
