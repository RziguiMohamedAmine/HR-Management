package hr.server.serverhr.services;

import hr.server.serverhr.entities.Conge;

import java.util.List;

public interface ICongeService {

    Conge DemanderConge(Conge conge);
    Conge UpdateConge(Conge conge);
    List<Conge> RetrieveHistoriqueConge(int id);
    void DeleteConge(int id);
}
