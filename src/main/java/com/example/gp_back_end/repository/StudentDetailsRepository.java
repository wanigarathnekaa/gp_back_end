package com.example.gp_back_end.repository;

import com.example.gp_back_end.model.StudentDetailsModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentDetailsRepository extends MongoRepository<StudentDetailsModel, String> {
    Optional<StudentDetailsModel> findByRegNo (String regNo);
}
