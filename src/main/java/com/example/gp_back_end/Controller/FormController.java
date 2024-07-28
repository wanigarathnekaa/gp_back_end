package com.example.gp_back_end.Controller;

import com.example.gp_back_end.dto.FormRequest;
import com.example.gp_back_end.dto.FormStats;
import com.example.gp_back_end.dto.FormSubmissionRequest;
import com.example.gp_back_end.dto.UpdateFormContentRequest;
import com.example.gp_back_end.model.FormModel;
import com.example.gp_back_end.model.FormSubmissionModel;
import com.example.gp_back_end.services.FormService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forms")
public class FormController {

    private final FormService formService;

    @GetMapping("/stats")
    public FormStats getFormStats() {
        return formService.getFormStats();
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitForm(@RequestBody FormSubmissionRequest request) {
        try {
            FormSubmissionModel savedSubmission = formService.saveFormSubmission(request);
            return ResponseEntity.ok(savedSubmission);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to submit form");
        }
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<?> publishForm(@PathVariable String id) {
        try {
            FormModel publishedForm = formService.publishForm(id);
            return ResponseEntity.ok(publishedForm);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to publish form");
        }
    }

    @PutMapping("/{id}/content")
    public ResponseEntity<?> updateFormContent(@PathVariable String id, @RequestBody UpdateFormContentRequest request) {
        try {
            FormModel updatedForm = formService.updateFormContent(id, request.getJsonContent());
            return ResponseEntity.ok(updatedForm);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to update form content");
        }
    }

    @GetMapping("/{id}")
    public FormModel getFormById(@PathVariable String id) {
        return formService.getFormById(id);
    }

    @GetMapping("/{id}/submissions")
    public FormModel getFormWithSubmissions(@PathVariable String id) {
        return formService.getFormWithSubmissions(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createForm(@RequestBody @Valid FormRequest formRequest) {
        String formId = formService.createForm(formRequest);
        return ResponseEntity.ok(formId);
    }

    @GetMapping("/all")
    public List<FormModel> getAllForms() {
        return formService.getAllForms();
    }

}