package com.example.gp_back_end.repository;

import com.example.gp_back_end.model.UserRoleModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRoleRepository extends MongoRepository<UserRoleModel, String>{
}
