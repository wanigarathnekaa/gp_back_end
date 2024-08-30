package com.example.gp_back_end.repository;

import com.example.gp_back_end.model.UploadStudentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentProfileRepository extends MongoRepository<UploadStudentModel, String>{
}
