package hr.server.serverhr.repositories;

import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.entities.Présence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PrésenceRepository extends JpaRepository<Présence,Integer> {
    List<Présence> findAllByEmployee(Optional<Employee> employee);
    List<Présence> findAllByDay(Date date);

    List<Présence> findByEmployeeIdEmployee(int IdEmployee);

    boolean existsByEmployeeIdEmployeeAndDay(int IdEmployee, Date day);
    List<Présence> findByDay(Date day);
    Optional<Présence> findByEmployeeIdEmployeeAndDay(int employeeId, Date date);

    @Query("SELECT p FROM Présence p WHERE FUNCTION('YEAR', p.day) = :year AND FUNCTION('MONTH', p.day) = :month")
    List<Présence> findByYearAndMonth(@Param("year") int year, @Param("month") int month);
}
