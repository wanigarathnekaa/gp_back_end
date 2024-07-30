package com.example.gp_back_end.Repositories;

import com.example.gp_back_end.Models.UploadStudentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentLogin extends MongoRepository<UploadStudentModel, String> {
    Optional<UploadStudentModel> findByRegNumber(String regNumber);
}
