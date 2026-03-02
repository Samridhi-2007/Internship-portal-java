package com.internship.internshipmanagement.controller;

import com.internship.internshipmanagement.model.Task;
import com.internship.internshipmanagement.service.TaskService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/student/{studentId}")
    public List<Task> getTasksByStudent(@PathVariable("studentId") Long studentId) {
        return taskService.getTasksByStudentId(studentId);
    }

    @GetMapping("/internship/{internshipId}")
    public List<Task> getTasksByInternship(@PathVariable("internshipId") Long internshipId) {
        return taskService.getTasksByInternshipId(internshipId);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        task.setId(id);
        return taskService.saveTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }
}