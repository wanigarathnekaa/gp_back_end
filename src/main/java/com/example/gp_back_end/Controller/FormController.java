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
@CrossOrigin(origins = "*")
public class FormController {

    private final FormService formService;

    @GetMapping("/stats")
    public FormStats getFormStats() {
        return formService.getFormStats();
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitForm(@RequestBody FormSubmissionRequest request) {
        System.out.println("request" + request.getFormURL());
        System.out.println("request" + request.getContent());
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
    public List<FormSubmissionModel> getFormWithSubmissions(@PathVariable String id) {
        System.out.println(id);
        return formService.getFormWithSubmissions(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createForm(@RequestBody @Valid FormRequest formRequest) {
        System.out.println(formRequest);
        String formId = formService.createForm(formRequest, 0);
        return ResponseEntity.ok(formId);
    }

    @PostMapping("/createFormTemplate")
    public ResponseEntity<String> createFormTemplate(@RequestBody @Valid FormRequest formRequest) {
        System.out.println(formRequest);
        String formId = formService.createForm(formRequest, 1);
        return ResponseEntity.ok(formId);
    }

    @GetMapping("/all")
    public List<FormModel> getAllForms() {
        return formService.getAllForms();
    }

    @GetMapping("/view/{formUrl}")
    public FormModel getFormContentByUrl(@PathVariable String formUrl) {
        System.out.println(formUrl);
        return formService.getFormContentByUrl(formUrl);
    }

    @GetMapping("/{id}/template_content")
    public String getFormTemplateContent(@PathVariable String id) {
        return formService.getFormTemplateContent(id);
    }

}
