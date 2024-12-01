package com.example.gp_back_end.repository;

import com.example.gp_back_end.model.CourseModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<CourseModel, String> {
    List<CourseModel> findByYearAndSemester(String year, String semester);

    List<CourseModel> findByCourseCodeIn(List<String> courseCodes);
}
