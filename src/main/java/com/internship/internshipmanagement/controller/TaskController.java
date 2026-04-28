package com.internship.internshipmanagement.controller;

import com.internship.internshipmanagement.model.Task;
import com.internship.internshipmanagement.service.TaskService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping({"/intern/tasks", "/recruiter/tasks", "/admin/tasks"})
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class TaskController {

    private final TaskService taskService;
    public TaskController(TaskService taskService) { this.taskService = taskService; }

    @GetMapping
    public List<Task> getAllTasks() { return taskService.getAllTasks(); }

    @GetMapping("/student/{studentId}")
    public List<Task> getTasksByStudent(@PathVariable Long studentId) {
        return taskService.getTasksByStudentId(studentId);
    }

    @GetMapping("/internship/{internshipId}")
    public List<Task> getTasksByInternship(@PathVariable Long internshipId) {
        return taskService.getTasksByInternshipId(internshipId);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) { return taskService.saveTask(task); }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return taskService.saveTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) { taskService.deleteTask(id); }
}
