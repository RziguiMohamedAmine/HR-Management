package hr.server.serverhr.repositories;

import hr.server.serverhr.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByEndDateBefore(Date currentDate);
}