package com.example.gp_back_end.Repositories;

import com.example.gp_back_end.Models.UploadModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UploadRepository extends MongoRepository<UploadModel, String> {
    List<UploadModel> findAll();
}
