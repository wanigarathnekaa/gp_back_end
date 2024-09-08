package com.example.gp_back_end.repository;

import com.example.gp_back_end.model.CloakModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CloakRepository extends MongoRepository<CloakModel, String> {
    Optional<CloakModel> findById (String id);
}
