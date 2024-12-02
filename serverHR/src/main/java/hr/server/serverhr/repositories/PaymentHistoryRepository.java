package hr.server.serverhr.repositories;

import hr.server.serverhr.entities.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory,Long> {

    List<PaymentHistory> findByEmployeeIdEmployee(int employeeId);
}
