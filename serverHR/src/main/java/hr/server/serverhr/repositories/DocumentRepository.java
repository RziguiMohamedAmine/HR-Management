package hr.server.serverhr.repositories;

import hr.server.serverhr.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document,Long> {
    List<Document> findByEmployeeIdEmployee(int employeeId);
    List<Document> findByEmployee_Nom(String name);
}
