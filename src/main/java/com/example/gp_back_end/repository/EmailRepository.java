package com.example.gp_back_end.repository;

import com.example.gp_back_end.model.EmailModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmailRepository extends MongoRepository<EmailModel, String> {
    List<EmailModel> findAll();
}
