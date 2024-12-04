package hr.server.serverhr.services;

import hr.server.serverhr.entities.Présence;
import hr.server.serverhr.repositories.PrésenceRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IPrésenceService {
    Présence ajouterPrésence(Présence présence);

    Présence addArrival(int presenceId, LocalDateTime arrivalTime);
    Présence addDeparture(int presenceId, LocalDateTime departureTime);
    List<Présence> retrieveAllPrésence(int id);
    List<Présence> getAllDayPresence(String date);
    void DeletePresence(int id);
    void updateSalaryWithExtraHoursBonus(int employeeId);
    Présence justifyAbsence(int présenceId, String justification);
}
