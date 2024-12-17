package hr.server.serverhr.services;

import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.entities.Projet;

import java.util.List;
import java.util.Set;

public interface IProjectService {
    Projet AjouterProjet(Projet projet);
    Projet UpdateProjet(Projet projet);
    List<Projet> RetrieveAllProject();
    Projet RetrieveProjet(int id);
    void DeleteProjet(int id);
    public Set<Employee> getEmployeesByProject(int projectId);
}
