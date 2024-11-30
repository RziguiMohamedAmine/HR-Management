package hr.server.serverhr.services;

import hr.server.serverhr.entities.Task;
import hr.server.serverhr.repositories.ProjectRepository;
import hr.server.serverhr.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class TaskService implements ITaskService{

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Task ajouterTask(Task task) {
        if (task.getProject() != null) {
            projectRepository.findById(task.getProject().getIdProjet())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
        }
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(int id,Task updatedTask) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        if (updatedTask.getProject() != null) {
            projectRepository.findById(updatedTask.getProject().getIdProjet())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
            existingTask.setProject(updatedTask.getProject());
        }
        return taskRepository.save(existingTask);
    }

    @Override
    public Task getTaskById(int id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public List<Task> retrieveAllTask() {
        return taskRepository.findAll();
    }


    @Override
    public void DeleteTask(int id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getTasksByProjectId(int projectId) {
        return taskRepository.findByProject(projectId);
    }
}
