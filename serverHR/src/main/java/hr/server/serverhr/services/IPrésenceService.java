package hr.server.serverhr.services;

import hr.server.serverhr.entities.Présence;
import hr.server.serverhr.repositories.PrésenceRepository;

import java.util.Date;
import java.util.List;

public interface IPrésenceService {
    Présence ajouterPrésence(Présence présence);
    Présence updatePrésence(Présence présence);
    List<Présence> retrieveAllPrésence(int id);
    List<Présence> getAllDayPresence(String date);
    void DeletePresence(int id);
}
