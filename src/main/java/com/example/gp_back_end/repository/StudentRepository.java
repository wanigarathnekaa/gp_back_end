package com.example.gp_back_end.repository;

import com.example.gp_back_end.model.UploadStudentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<UploadStudentModel, String> {
    Optional<UploadStudentModel> findByRegNumber(String regNumber);
}
