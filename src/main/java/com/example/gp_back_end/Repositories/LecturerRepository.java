package com.example.gp_back_end.Repositories;


import com.example.gp_back_end.Models.UploadLecturerModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LecturerRepository extends MongoRepository<UploadLecturerModel, String> {
    List<UploadLecturerModel> findAll();
}
