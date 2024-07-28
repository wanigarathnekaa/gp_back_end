package com.example.gp_back_end.services;

import com.example.gp_back_end.dto.FormRequest;
import com.example.gp_back_end.dto.FormStats;
import com.example.gp_back_end.dto.FormSubmissionRequest;
import com.example.gp_back_end.model.FormModel;
import com.example.gp_back_end.model.FormSubmissionModel;
import com.example.gp_back_end.repository.FormRepository;
import com.example.gp_back_end.repository.FormSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormService {

    private final FormRepository formRepository;
    private final FormSubmissionRepository formSubmissionRepository;

    public FormStats getFormStats() {
        // Fetch all forms for the given user
        List<FormModel> forms = formRepository.findAll();

        // Initialize counters
        int totalVisits = 0;
        int totalSubmissions = 0;

        // Calculate total visits and submissions
        for (FormModel form : forms) {
            totalVisits += (form.getVisits() != null) ? form.getVisits() : 0;
            totalSubmissions += (form.getSubmissions() != null) ? form.getSubmissions() : 0;
        }

        // Calculate rates
        double submissionRate = (totalVisits > 0) ? ((double) totalSubmissions / totalVisits) * 100 : 0;
        double bounceRate = 100 - submissionRate;

        // Create and return the stats object
        return new FormStats(totalVisits, totalSubmissions, submissionRate, bounceRate);
    }

    public FormSubmissionModel saveFormSubmission(FormSubmissionRequest request) throws Exception {
        Optional<FormModel> formOptional = formRepository.findByShareURL(request.getFormURL());
        if (formOptional.isEmpty()) {
            throw new Exception("Form not found");
        }

        FormModel form = formOptional.get();

        FormSubmissionModel formSubmission = FormSubmissionModel.builder()
                .formId(form.getId())
                .content(request.getContent())
                .form(form)
                .build();

        return formSubmissionRepository.save(formSubmission);
    }

    public FormModel publishForm(String id) throws Exception {
        Optional<FormModel> formOptional = formRepository.findById(id);
        if (formOptional.isEmpty()) {
            throw new Exception("Form not found");
        }

        FormModel form = formOptional.get();
        form.setPublished(true);
        return formRepository.save(form);
    }

    public FormModel updateFormContent(String id, String jsonContent) throws Exception {
        Optional<FormModel> formOptional = formRepository.findById(id);
        if (formOptional.isEmpty()) {
            throw new Exception("Form not found");
        }

        FormModel form = formOptional.get();
        form.setContent(jsonContent);

        return formRepository.save(form);
    }

    public FormModel getFormById(String id) {
        Optional<FormModel> formOptional = formRepository.findById(id);
        return formOptional.orElseThrow(() -> new RuntimeException("Form not found"));
    }

    public FormModel getFormWithSubmissions(String id) {
        FormModel form = getFormById(id);
        List<FormSubmissionModel> submissions = formSubmissionRepository.findByFormId(id);
        form.setFormSubmissions(submissions);
        form.setSubmissions(submissions.size()); // Set the count of submissions
        return form;
    }

    public void addFormSubmission(String formId, FormSubmissionModel submission) {
        FormModel form = getFormById(formId);
        form.setSubmissions(form.getSubmissions() + 1); // Increment the submissions count
        formSubmissionRepository.save(submission);
        formRepository.save(form);
    }

    public String createForm(FormRequest formRequest) {
        FormModel form = FormModel.builder()
                .userId(formRequest.getUserId())
                .name(formRequest.getName())
                .description(formRequest.getDescription())
                .createdAt(LocalDateTime.now())
                .published(false)
                .visits(0)
                .submissions(0)
                .shareURL(generateShareURL())
                .build();

        FormModel savedForm = formRepository.save(form);
        return savedForm.getId();
    }

    private String generateShareURL() {
        // Logic to generate a share URL
        return "http://localhost/share/" + System.currentTimeMillis();
    }

    public List<FormModel> getAllForms() {
        return formRepository.findAll();
    }
}

