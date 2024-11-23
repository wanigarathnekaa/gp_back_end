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
import java.util.Date;
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
        System.out.println(form);
        Integer count = form.getSubmissions();
        System.out.println(count);
        form.setSubmissions(count + 1);
        formRepository.save(form);

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

    public List<FormSubmissionModel> getFormWithSubmissions(String id) {
        FormModel form = getFormById(id);
        System.out.println(form.getId());
        List<FormSubmissionModel> submissions = formSubmissionRepository.findByFormId(id);
        System.out.println(submissions);
        form.setFormSubmissions(submissions);
        form.setSubmissions(submissions.size()); // Set the count of submissions
        return submissions;
    }

    public void addFormSubmission(String formId, FormSubmissionModel submission) {
        FormModel form = getFormById(formId);
        form.setSubmissions(form.getSubmissions() + 1); // Increment the submissions count
        formSubmissionRepository.save(submission);
        formRepository.save(form);
    }

    public String createForm(FormRequest formRequest, int template) {
        FormModel form;
        if (template == 1) {
            form = FormModel.builder()
                    .userId("REG123456")
                    .name(formRequest.getName())
                    .description(formRequest.getDescription())
                    .content(formRequest.getContent())
                    .template(formRequest.getTemplate())
                    .createdAt(LocalDateTime.now())
                    .published(false)
                    .visits(0)
                    .submissions(0)
                    .shareURL(generateShareURL())
                    .build();
        }else{
            form = FormModel.builder()
                    .userId("REG123456")
                    .name(formRequest.getName())
                    .description(formRequest.getDescription())
                    .template(formRequest.getTemplate())
                    .createdAt(LocalDateTime.now())
                    .published(false)
                    .visits(0)
                    .submissions(0)
                    .shareURL(generateShareURL())
                    .build();
        }

        FormModel savedForm = formRepository.save(form);
        return savedForm.getId();
    }

    private String generateShareURL() {
        // Logic to generate a share URL
        return String.valueOf(System.currentTimeMillis());
    }

    public List<FormModel> getAllForms() {
        return formRepository.findAll();
    }

    public FormModel getFormContentByUrl(String formUrl) {
        Optional<FormModel> optionalForm = formRepository.findByShareURL(formUrl);

        if (optionalForm.isPresent()) {
            FormModel form = optionalForm.get();
            form.setVisits(form.getVisits() + 1);
            formRepository.save(form);
            return form;
        } else {
            throw new RuntimeException("Form not found with URL: " + formUrl);
        }
    }

    public String getFormTemplateContent(String id) {
        Optional<FormModel> optionalForm = formRepository.findById(id);
        if (optionalForm.isPresent()) {
            return optionalForm.get().getContent();
        }
        throw new RuntimeException("Form not found with ID: " + id);
    }
}

