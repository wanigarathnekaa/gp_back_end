package com.example.gp_back_end.repository;

import com.example.gp_back_end.model.UploadLecturerModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LecturerLoginRepository extends MongoRepository<UploadLecturerModel, String> {
    Optional<UploadLecturerModel> findByRegNumber(String lecturerId);
}
