package hr.server.serverhr.services;

import hr.server.serverhr.entities.Projet;
import hr.server.serverhr.repositories.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProjectService implements IProjectService{

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Projet AjouterProjet(Projet projet) {
        return projectRepository.save(projet);
    }

    @Override
    public Projet UpdateProjet(Projet projet) {
        return projectRepository.save(projet);
    }

    @Override
    public List<Projet> RetrieveAllProject() {
        return projectRepository.findAll();
    }

    @Override
    public Projet RetrieveProjet(int id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public void DeleteProjet(int id) {
        projectRepository.deleteById(id);
    }
}
