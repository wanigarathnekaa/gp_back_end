package com.example.gp_back_end.repository;

import com.example.gp_back_end.model.FormModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormRepository extends MongoRepository<FormModel, String> {
    List<FormModel> findByUserId(String userId);
    Optional<FormModel> findByShareURL(String shareURL);
    List<FormModel> findByTemplate(boolean template);
}
