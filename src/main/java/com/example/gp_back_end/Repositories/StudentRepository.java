package com.example.gp_back_end.Repositories;

import com.example.gp_back_end.Models.UploadStudentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentRepository extends MongoRepository<UploadStudentModel, String> {
    List<UploadStudentModel> findAll();
}
