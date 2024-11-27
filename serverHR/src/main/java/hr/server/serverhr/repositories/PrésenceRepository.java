package hr.server.serverhr.repositories;

import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.entities.Présence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PrésenceRepository extends JpaRepository<Présence,Integer> {
    List<Présence> findAllByEmployee(Optional<Employee> employee);
    List<Présence> findAllByDay(Date date);

}
