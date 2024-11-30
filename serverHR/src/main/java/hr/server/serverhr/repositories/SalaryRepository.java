package hr.server.serverhr.repositories;

import hr.server.serverhr.entities.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary,Long>{
    Optional<Salary> findByEmployeeIdEmployee(int employeeId);
}
