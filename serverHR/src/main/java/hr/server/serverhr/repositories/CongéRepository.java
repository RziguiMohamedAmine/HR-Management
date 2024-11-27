package hr.server.serverhr.repositories;

import hr.server.serverhr.entities.Conge;
import hr.server.serverhr.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CongéRepository extends JpaRepository<Conge,Integer> {
    List<Conge> findAllByEmployee(Optional<Employee> employee);
}
