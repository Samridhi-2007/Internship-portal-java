package com.internship.internshipmanagement.controller;

import com.internship.internshipmanagement.model.Internship;
import com.internship.internshipmanagement.service.InternshipService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping({"/intern/internships", "/recruiter/internships", "/admin/internships"})
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class InternshipController {

    private final InternshipService internshipService;
    public InternshipController(InternshipService internshipService) { this.internshipService = internshipService; }

    @GetMapping
    public List<Internship> getAllInternships() { return internshipService.getAllInternships(); }

    @GetMapping("/{id}")
    public Internship getInternshipById(@PathVariable Long id) { return internshipService.getInternshipById(id); }

    @PostMapping
    public Internship createInternship(@RequestBody Internship internship) { return internshipService.saveInternship(internship); }

    @PutMapping("/{id}")
    public Internship updateInternship(@PathVariable Long id, @RequestBody Internship internship) {
        internship.setId(id);
        return internshipService.saveInternship(internship);
    }

    @DeleteMapping("/{id}")
    public void deleteInternship(@PathVariable Long id) { internshipService.deleteInternship(id); }
}
