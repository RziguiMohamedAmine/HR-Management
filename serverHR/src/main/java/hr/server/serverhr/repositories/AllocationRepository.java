package hr.server.serverhr.repositories;

import hr.server.serverhr.entities.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationRepository extends JpaRepository<Allocation,Long> {
}
