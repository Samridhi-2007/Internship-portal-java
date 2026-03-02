package com.internship.internshipmanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internship.internshipmanagement.model.Internship;
import com.internship.internshipmanagement.service.InternshipService;

@RestController
@RequestMapping("/internships")
@CrossOrigin(origins = "*")
public class InternshipController {

    private final InternshipService internshipService;

    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @GetMapping
    public List<Internship> getAllInternships() {
        return internshipService.getAllInternships();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Internship> getInternshipById(@PathVariable Long id) {
        Internship internship = internshipService.getInternshipById(id);
        if (internship == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(internship);
    }

    @PostMapping
    public Internship createInternship(@RequestBody Internship internship) {
        return internshipService.saveInternship(internship);
    }

    @PutMapping("/{id}")
    public Internship updateInternship(@PathVariable Long id, @RequestBody Internship internship) {
        internship.setId(id);
        return internshipService.saveInternship(internship);
    }

    @DeleteMapping("/{id}")
    public void deleteInternship(@PathVariable Long id) {
        internshipService.deleteInternship(id);
    }
}