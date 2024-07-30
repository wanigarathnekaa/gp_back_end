package com.example.gp_back_end.repository;

import com.example.gp_back_end.model.UploadLecturerModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LecturerRepository extends MongoRepository<UploadLecturerModel, String> {
}
