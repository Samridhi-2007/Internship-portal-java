package com.internship.internshipmanagement.controller;

import com.internship.internshipmanagement.model.Feedback;
import com.internship.internshipmanagement.service.FeedbackService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/feedbacks")
@CrossOrigin(origins = "http://localhost:3000")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/student/{studentId}")
    public List<Feedback> getFeedbacksByStudent(@PathVariable("studentId") Long studentId) {
        return feedbackService.getFeedbacksByStudentId(studentId);
    }

    @GetMapping("/internship/{internshipId}")
    public List<Feedback> getFeedbacksByInternship(@PathVariable("internshipId") Long internshipId) {
        return feedbackService.getFeedbacksByInternshipId(internshipId);
    }

    @PostMapping
    public Feedback createFeedback(@RequestBody Feedback feedback) {
        return feedbackService.saveFeedback(feedback);
    }

    @PutMapping("/{id}")
    public Feedback updateFeedback(@PathVariable("id") Long id, @RequestBody Feedback feedback) {
        feedback.setId(id);
        return feedbackService.saveFeedback(feedback);
    }

    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable("id") Long id) {
        feedbackService.deleteFeedback(id);
    }
}