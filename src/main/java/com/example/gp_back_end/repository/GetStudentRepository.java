package com.example.gp_back_end.repository;

import com.example.gp_back_end.model.GetStudentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GetStudentRepository extends MongoRepository<GetStudentModel, String> {
    Optional<GetStudentModel> findByRegNumber(String regNumber);
}
