package hr.server.serverhr.services;

import hr.server.serverhr.entities.Projet;
import hr.server.serverhr.entities.Présence;
import hr.server.serverhr.entities.Task;

import java.util.List;

public interface ITaskService {
    Task ajouterTask(Task task);
    Task updateTask(int id,Task updatedTask);
    Task getTaskById(int id);
    List<Task> retrieveAllTask();
    void DeleteTask(int id);
    List<Task> getTasksByProjectId(int projectId);
}
