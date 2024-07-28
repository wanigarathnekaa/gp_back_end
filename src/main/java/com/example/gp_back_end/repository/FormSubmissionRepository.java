package com.example.gp_back_end.repository;

import com.example.gp_back_end.model.FormSubmissionModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FormSubmissionRepository extends MongoRepository<FormSubmissionModel, String> {
    List<FormSubmissionModel> findByFormId(String formId);
}
