package com.example.gp_back_end.repository;

import com.example.gp_back_end.model.CourseModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<CourseModel, String> {
}
