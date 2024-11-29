package hr.server.serverhr.services;

import hr.server.serverhr.entities.Conge;

import java.util.List;

public interface ICongeService {

    Conge DemanderConge(Conge conge);

    boolean approuverConge(Conge conge);
    boolean refuserConge(Conge conge);

    List<Conge> RetrieveHistoriqueConge(int id);
    void DeleteConge(int id);
}
