package com.example.gp_back_end.repository;

import com.example.gp_back_end.model.PrivilegedUsersModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrivilegedUsersRepository extends MongoRepository<PrivilegedUsersModel, String>{
}
