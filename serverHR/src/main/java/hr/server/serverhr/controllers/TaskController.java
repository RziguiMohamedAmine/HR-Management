package hr.server.serverhr.controllers;


import hr.server.serverhr.entities.Task;
import hr.server.serverhr.services.IProjectService;
import hr.server.serverhr.services.ITaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/HrMangement/Task")
public class TaskController {

    @Autowired
    ITaskService taskService;
    @Autowired
    IProjectService projectService;

    @PostMapping("/ajouterTask")
    public Task createTask(@RequestBody Task task) {
        return taskService.ajouterTask(task);
    }

    @GetMapping("/afficherTask/{id}")
    public Task getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/afficherAllTask")
    public List<Task> getAllTasks() {
        return taskService.retrieveAllTask();
    }
    @PutMapping("/updateTask/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id) {
        taskService.DeleteTask(id);
    }

    @GetMapping("/project/{projectId}")
    public List<Task> getTasksByProjectId(@PathVariable int projectId) {
        return taskService.getTasksByProjectId(projectId);
    }

}
